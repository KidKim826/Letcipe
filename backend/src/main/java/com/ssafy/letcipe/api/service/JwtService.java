package com.ssafy.letcipe.api.service;

import com.ssafy.letcipe.domain.type.StatusType;
import com.ssafy.letcipe.domain.user.User;
import com.ssafy.letcipe.domain.user.UserType;
import com.ssafy.letcipe.exception.AuthorityViolationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {

    private final String secretKey = "***********";
    private final int EXPIRE_MINUTES = 60 * 24;
    private final int REFRESH_MINUTES = 60 * 24 * 7;

    /**
     * request header에서 token을 가져오는 함수
     * interceptor에서 valid와 존재여부를 검사할 것이므로 여기선 따로 하지 않는다.
     *
     * @param request HttpServletRequest
     * @return 받아온 값
     */
    public String getJwtToken(HttpServletRequest request) {
        return request.getHeader("access-token");
    }

    /**
     * user 정보를 기반으로 jwt token을 생성함
     * 
     * @param user 유저 엔티티
     * @return jwt token
     */
    public String createToken(User user) {
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES))
                .claim("userId", user.getId())
                .claim("status", user.getUserType().getDesc())
                .signWith(SignatureAlgorithm.HS256, generateKey()).compact();
        return jwt;
    }

    public String createRefreshToken() {
        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * REFRESH_MINUTES))
                .signWith(SignatureAlgorithm.HS256, generateKey()).compact();
        return jwt;
    }

    /**
     * swcretkey를 기반으로 key를 생성함
     *
     * @return key
     */
    private byte[] generateKey() {
        try {
            return secretKey.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Jwt Token이 유효한지 날짜를 계산함
     * 
     * @param jwt jwt token string
     * @return boolean 유효하지 않으면 exception throw
     */
    public boolean checkJwtToken(String jwt) {
        try {
            return getClaims(jwt).getExpiration().after(new Date());
        } catch (Exception e) {
            throw new AuthorityViolationException("잘못된 토큰입니다.");
        }
    }

    /**
     * jwt token에서 userid를 구함
     * 
     * @param request HttpServletRequest
     * @return user id 
     */
    public Long getUserId(HttpServletRequest request){
        try {
            return Long.parseLong(String.valueOf(getClaims(getJwtToken(request)).get("userId")));
        } catch (Exception e){
            e.printStackTrace();
            throw new AuthorityViolationException("권한 없음");
        }
    }

    public String getUserType(HttpServletRequest request){
        try {
            String type = (String) getClaims(getJwtToken(request)).get("status");
            return type;
        } catch (Exception e){
            e.printStackTrace();
            throw new AuthorityViolationException("권한 없음");
        }
    }

    public boolean isAdmin(HttpServletRequest request){
        return getUserType(request).equals("admin");
    }

    public Long getUserId(String jwtToken) {
        try {
            return Long.parseLong(String.valueOf(getClaims(jwtToken).get("userId")));
        } catch (Exception e) {
            throw new AuthorityViolationException("권한 없음");
        }
    }
        /**
         * jwt token에서 claim을 구함
         *
         * @param jwt jwt token
         * @return claims
         */
    private Claims getClaims(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(jwt);
            return claims.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}


