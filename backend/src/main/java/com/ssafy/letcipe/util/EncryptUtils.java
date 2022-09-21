package com.ssafy.letcipe.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class EncryptUtils {

    /**
     * 스트링을 sha256 방식으로 암호화
     *
     * @param text 암호화할 스트링
     * @return 암호화된 스트링
     * @throws NoSuchAlgorithmException 해당 암호화 알고리즘이 존재하지 않는 경우
     */
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    /**
     *
     * @param bytes
     * @return
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
