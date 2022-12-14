package com.ssafy.letcipe.api.service;

import com.ssafy.letcipe.api.dto.recipe.ResGetHotRecipeComponentDto;
import com.ssafy.letcipe.api.dto.user.*;
import com.ssafy.letcipe.domain.comment.BoardType;
import com.ssafy.letcipe.domain.comment.Comment;
import com.ssafy.letcipe.domain.comment.CommentRepository;
import com.ssafy.letcipe.domain.recipe.Recipe;
import com.ssafy.letcipe.domain.recipe.RecipeRepository;
import com.ssafy.letcipe.domain.recipeBookmark.RecipeBookmark;
import com.ssafy.letcipe.domain.recipeBookmark.RecipeBookmarkRepository;
import com.ssafy.letcipe.domain.recipeLike.RecipeLike;
import com.ssafy.letcipe.domain.recipeLike.RecipeLikeRepository;
import com.ssafy.letcipe.domain.recipeList.RecipeList;
import com.ssafy.letcipe.domain.recipeList.RecipeListRepository;
import com.ssafy.letcipe.domain.recipeListBookmark.RecipeListBookmark;
import com.ssafy.letcipe.domain.recipeListBookmark.RecipeListBookmarkRepository;
import com.ssafy.letcipe.domain.type.StatusType;
import com.ssafy.letcipe.domain.user.*;
import com.ssafy.letcipe.exception.AuthorityViolationException;
import com.ssafy.letcipe.exception.BadRequestException;
import com.ssafy.letcipe.util.EncryptUtils;
import com.ssafy.letcipe.util.FileHandler;
import com.ssafy.letcipe.util.StringUtils;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeListRepository recipeListRepository;
    private final RecipeBookmarkRepository recipeBookmarkRepository;

    private final RecipeLikeRepository recipeLikeRepository;
    private final RecipeListBookmarkRepository recipeListBookmarkRepository;
    private final CommentRepository commentRepository;
    private final EncryptUtils encryptUtils;
    private final StringUtils stringUtils;

    private final FileHandler fileHandler;

    @Transactional
    public ResGetHotRecipeComponentDto getAttribute(Long userId) {
        JobType job;
        GenderType gen;
        int fam;
        int age;
        List<String> str = new ArrayList<>();
        Random rnd = new Random();
        if (userId == -1L) {
            job = JobType.values()[rnd.nextInt(4)];
            gen = GenderType.values()[rnd.nextInt(2)];
            fam = rnd.nextInt(4) + 1;
            age = (rnd.nextInt(4) + 1) * 10;
        } else {
            User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException());
            job = user.getJob();
            gen = user.getGender();
            fam = user.getFamily();
            age = (LocalDate.now().getYear() - user.getBirth().getYear()) / 10 * 10;
        }
        str.add(gen.getDesc().substring(0, 1).toUpperCase());
        str.add("" + age);
        str.add("" + fam);
        str.add(job.getDesc().toUpperCase());
        int flag = rnd.nextInt(16);
        StringBuilder att = new StringBuilder();
        StringBuilder title = new StringBuilder();
        boolean[] check = new boolean[4];
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (flag % 2 == 0) {
                att.append(str.get(i));
                check[i] = true;
                cnt++;
            } else {
                att.append("-");
            }
            att.append(",");
            flag /= 2;
        }
        if (check[2]) {
            if (str.get(2).equals("1")) {
                title.append("?????? ?????? ");
            } else {
                title.append(str.get(2)).append("????????? ?????? ");
            }
            if (cnt == 1)
                title.append("????????? ");
        }
        if (check[1])
            if (check[0] || check[3])
                title.append(str.get(1) + "??? ");
            else
                title.append(str.get(1) + "?????? ");
        if (check[0])
            if (check[3])
                title.append(str.get(0).equals("F") ? "?????? " : "?????? ");
            else
                title.append(str.get(0).equals("F") ? "????????? " : "????????? ");
        if (check[3]) {
            switch (str.get(3)) {
                case "STUDENT":
                    title.append("????????? ");
                    break;
                case "JUBU":
                    title.append("????????? ");
                    break;
                case "WORKER":
                    title.append("???????????? ");
                    break;
                case "COOK":
                    title.append("???????????? ");
                    break;
            }
        }
        if(cnt==0){
            title.append("????????? ");
        }
        title.append("???????????? ????????????");
        att.setLength(att.length() - 1);
        ResGetHotRecipeComponentDto hotRecipeDto = ResGetHotRecipeComponentDto.builder()
                .title(title.toString())
                .attribute(att.toString())
                .build();
        return hotRecipeDto;
    }

    /**
     * getAttribute?????? ?????? ????????? ?????????
     * ???????????? ???????????? 1????????? -?????? ???????????????.
     * @param userId
     * @return
     */
    @Transactional
    public ResGetHotRecipeComponentDto getAttribute2(Long userId) {
        JobType job;
        GenderType gen;
        int fam;
        int age;
        List<String> str = new ArrayList<>();
        Random rnd = new Random();
        if (userId == -1L) {
            job = JobType.values()[rnd.nextInt(4)];
            gen = GenderType.values()[rnd.nextInt(2)];
            fam = rnd.nextInt(1) + 1;
            age = (rnd.nextInt(4) + 1) * 10;
        } else {
            User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException());
            job = user.getJob();
            gen = user.getGender();
            fam = user.getFamily();
            age = (LocalDate.now().getYear() - user.getBirth().getYear()) / 10 * 10;
        }
        str.add(gen.getDesc().substring(0, 1).toUpperCase());
        str.add("" + age);
        str.add("" + fam);
        str.add(job.getDesc().toUpperCase());
        int flag = rnd.nextInt(16);
        if (fam != 1) {
            flag |= 0b0100;
        }
        StringBuilder att = new StringBuilder();
        StringBuilder title = new StringBuilder();
        boolean[] check = new boolean[4];
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (flag % 2 == 0) {
                att.append(str.get(i));
                check[i] = true;
                cnt++;
            } else {
                att.append("-");
            }
            att.append(",");
            flag /= 2;
        }
        if (check[2]) {
            if (str.get(2).equals("1")) {
                title.append("?????? ?????? ");
            } else {
                title.append(str.get(2)).append("????????? ?????? ");
            }
            if (cnt == 1)
                title.append("????????? ");
        }
        if (check[1])
            if (check[0] || check[3])
                title.append(str.get(1) + "??? ");
            else
                title.append(str.get(1) + "?????? ");
        if (check[0])
            if (check[3])
                title.append(str.get(0).equals("F") ? "?????? " : "?????? ");
            else
                title.append(str.get(0).equals("F") ? "????????? " : "????????? ");
        if (check[3]) {
            switch (str.get(3)) {
                case "STUDENT":
                    title.append("????????? ");
                    break;
                case "JUBU":
                    title.append("????????? ");
                    break;
                case "WORKER":
                    title.append("???????????? ");
                    break;
                case "COOK":
                    title.append("???????????? ");
                    break;
            }
        }
        if(cnt==0){
            title.append("????????? ");
        }
        title.append("???????????? ????????????");
        att.setLength(att.length() - 1);
        ResGetHotRecipeComponentDto hotRecipeDto = ResGetHotRecipeComponentDto.builder()
                .title(title.toString())
                .attribute(att.toString())
                .build();
        return hotRecipeDto;
    }

    @Transactional
    public void createUser(ReqPostUserDto requestDto) throws NoSuchAlgorithmException, FileUploadException {

        StringBuilder sb = new StringBuilder();

        // ???????????? ?????????
        String salt = encryptUtils.getSalt(requestDto.getUserId());
        sb.append(salt).append(requestDto.getPassword());
        String password = encryptUtils.encrypt(sb.toString());

        // ?????? ??????
        LocalDate localDate = LocalDate.parse(requestDto.getBirth());

        String profileImgUrl = fileHandler.uploadImage(requestDto.getProfileImg());

        // ?????? ????????? ??????
        User user = User.builder()
                .userId(requestDto.getUserId())
                .userType(UserType.USER)
                .birth(localDate)
                .email(requestDto.getEmail())
                .family(requestDto.getFamily())
                .gender(requestDto.getGender())
                .name(requestDto.getName())
                .job(requestDto.getJob())
                .nickname(requestDto.getNickname())
                .password(password)
                .phone(requestDto.getPhone())
                .profileImage(profileImgUrl)
                .build();
        userRepository.save(user);
    }

    public ResLoginUserDto loginUser(ReqLoginUserDto requestDto) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();

        String salt = encryptUtils.getSalt(requestDto.getUserId());
        sb.append(salt).append(requestDto.getPassword());
        String password = encryptUtils.encrypt(sb.toString());

        User user = userRepository.findByUserIdAndPasswordAndStatusType(requestDto.getUserId(), password, StatusType.N)
                .orElseThrow(() -> new NullPointerException());

        String token = jwtService.createToken(user);
        String refreshToken = jwtService.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        return new ResLoginUserDto().builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public ResLoginUserDto loginAdmin(ReqLoginUserDto requestDto) throws NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();

        String salt = encryptUtils.getSalt(requestDto.getUserId());
        sb.append(salt).append(requestDto.getPassword());
        String password = encryptUtils.encrypt(sb.toString());

        User user = userRepository.findByUserIdAndPasswordAndUserType(requestDto.getUserId(), password, UserType.ADMIN)
                .orElseThrow(() -> new NullPointerException());

        String token = jwtService.createToken(user);
        String refreshToken = jwtService.createRefreshToken();
        user.updateRefreshToken(refreshToken);
        return new ResLoginUserDto().builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public ResGetUserDto readUser(Long userId) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        return ResGetUserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .phone(user.getPhone())
                .email(user.getEmail())
                .gender(user.getGender())
                .job(user.getJob())
                .family(user.getFamily())
                .birth(user.getBirth())
                .userType(user.getUserType())
                .build();
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        user.delete();
    }

    @Transactional
    public void updateUser(Long userId, ReqPutUserDto requestDto) throws FileUploadException {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        // ?????? ?????? ??????
        user.update(requestDto);
        // ????????? ????????? ?????? ??????
        if(requestDto.getProfileImg() != null) {
            user.updateProfileImage(fileHandler.uploadImage(requestDto.getProfileImg()));
        }
    }

    public User findUser(long userId) throws NullPointerException {
        return userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
    }

    @Transactional
    public ResGetUserRecipesDto readUserRecipe(Long userId, Pageable pageable) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        List<Recipe> recipeList = recipeRepository.findAllByUserAndStatusType(pageable, user,StatusType.N);
        List<ResGetUserRecipeDto> dtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            dtoList.add(new ResGetUserRecipeDto(recipe));
        }
        return new ResGetUserRecipesDto(dtoList);
    }
    @Transactional
    public ResGetUserRecipeListsDto readUserRecipeList(Long userId, Pageable pageable) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        List<RecipeList> recipeListList = recipeListRepository.findAllByUserAndIsDeleted(pageable, user, StatusType.N);
        List<ResGetUserRecipeListDto> dtoList = new ArrayList<>();
        for (RecipeList recipeList : recipeListList) {
            boolean isBookmark = recipeListBookmarkRepository.existsByUserIdAndRecipeListId(userId, recipeList.getId());
            dtoList.add(new ResGetUserRecipeListDto(recipeList, isBookmark));
        }
        return new ResGetUserRecipeListsDto(dtoList);
    }

    @Transactional
    public ResGetUserRecipesDto readRecipeBookmark(Long userId, Pageable pageable) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        List<RecipeBookmark> recipeBookmarkList = recipeBookmarkRepository.findAllByUser(pageable, user);
        List<ResGetUserRecipeDto> dtoList = new ArrayList<>();
        for (RecipeBookmark recipeBookmark : recipeBookmarkList) {
            if (recipeBookmark.getRecipe().getStatusType() == StatusType.N)
                dtoList.add(new ResGetUserRecipeDto(recipeBookmark.getRecipe()));
        }
        return new ResGetUserRecipesDto(dtoList);
    }

    @Transactional
    public ResGetUserRecipeListsDto readRecipeListBookmark(Long userId, Pageable pageable) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        List<RecipeListBookmark> recipeListBookmarkList = recipeListBookmarkRepository.findAllByUser(pageable, user);
        List<ResGetUserRecipeListDto> dtoList = new ArrayList<>();
        for (RecipeListBookmark recipeListBookmark : recipeListBookmarkList) {
            if (recipeListBookmark.getRecipeList().getIsDeleted() == StatusType.N)
                dtoList.add(new ResGetUserRecipeListDto(recipeListBookmark.getRecipeList(), true));
        }
        return new ResGetUserRecipeListsDto(dtoList);
    }

    @Transactional
    public List<ResGetUserCommentDto> getUserComment(Long userId, Pageable pageable) {
        List<Comment> commentList = commentRepository.findAllByUserIdAndBoardTypeAndStatusType(pageable, userId, BoardType.RECIPE, StatusType.N);
        List<ResGetUserCommentDto> dtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            dtoList.add(new ResGetUserCommentDto(comment));
        }
        return dtoList;
    }

    public Long getRecipeNum(Long userId) {
        return recipeRepository.countByUserIdAndStatusType(userId, StatusType.N);
    }

    public Long getCommentNum(Long userId) {
        return commentRepository.countByUserIdAndBoardTypeAndStatusType(userId, BoardType.RECIPE, StatusType.N);
    }

    public String readUserId(ReqGetUserIdDto requestDto) {
        User user = userRepository.findByNameAndPhone(requestDto.getName(), requestDto.getPhone())
                .orElseThrow(() -> new NullPointerException());
        return user.getUserId();
    }

    @Transactional
    public String readPassword(ReqGetPasswordDto requestDto) throws NoSuchAlgorithmException {
        User user = userRepository.findByUserIdAndPhone(requestDto.getUserId(), requestDto.getPhone())
                .orElseThrow(() -> new NullPointerException());
        String newPassword = stringUtils.getRandomString(10);
        StringBuilder sb = new StringBuilder();

        String salt = encryptUtils.getSalt(user.getUserId());
        sb.append(salt).append(newPassword);
        user.updatePassword(encryptUtils.encrypt(sb.toString()));

        return newPassword;
    }

    @Transactional
    public void updatePassword(ReqUpdatePasswordDto requestDto, Long userId) throws NoSuchAlgorithmException {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException());

        StringBuilder sb = new StringBuilder();
        String salt = encryptUtils.getSalt(user.getUserId());
        sb.append(salt).append(requestDto.getPassword());
        String password = encryptUtils.encrypt(sb.toString());

        if (password.equals(user.getPassword())) {
            sb = new StringBuilder();
            sb.append(salt).append(requestDto.getNewPassword());
            String newPassword = encryptUtils.encrypt(sb.toString());
            user.updatePassword(newPassword);
        } else {
            throw new AuthorityViolationException("??????????????? ????????? ??? ????????????.");
        }
    }

    @Transactional
    public ResLoginUserDto updateToken(String token, String refreshToken) {
        if (jwtService.checkJwtToken(token)) {
            throw new AuthorityViolationException("????????? ??? ??????");
        }
        User user = userRepository.findById(jwtService.getUserId(token))
                .orElseThrow(() -> new NullPointerException());
        if (!jwtService.checkJwtToken(refreshToken) || !user.getRefreshToken().equals(refreshToken)) {
            throw new AuthorityViolationException("????????? ??? ??????");
        }

        String newToken = jwtService.createToken(user);
        String newRefreshToken = jwtService.createRefreshToken();

        return new ResLoginUserDto().builder()
                .accessToken(newToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    public void checkDuplicationId(String userId) {
        if (userRepository.existsByUserId(userId)) throw new BadRequestException("?????? ???????????? ????????? ?????????.");
    }

    public void checkDuplicationNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) throw new BadRequestException("?????? ???????????? ????????? ?????????.");
    }

    @Transactional
    public ResGetUserRecipesDto readRecipeLike(Long userId, Pageable pageable) {
        User user = userRepository.findByIdAndStatusType(userId, StatusType.N).orElseThrow(() -> new NullPointerException());
        List<RecipeLike> recipeLikeList = recipeLikeRepository.findAllByUser(pageable, user);
        List<ResGetUserRecipeDto> dtoList = new ArrayList<>();
        for (RecipeLike recipelike : recipeLikeList) {
            if (recipelike.getRecipe().getStatusType() == StatusType.N)
                dtoList.add(new ResGetUserRecipeDto(recipelike.getRecipe()));
        }
        return new ResGetUserRecipesDto(dtoList);
    }
}
