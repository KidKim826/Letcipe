package com.ssafy.letcipe.domain.recipe;

import com.ssafy.letcipe.api.dto.recipe.ReqPutRecipeDto;
import com.ssafy.letcipe.domain.recipeBookmark.RecipeBookmark;
import com.ssafy.letcipe.domain.recipeIngredient.RecipeIngredient;
import com.ssafy.letcipe.domain.recipeLike.RecipeLike;
import com.ssafy.letcipe.domain.recipeStep.RecipeStep;
import com.ssafy.letcipe.domain.recipeTag.RecipeTag;
import com.ssafy.letcipe.domain.type.StatusType;
import com.ssafy.letcipe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "serving", nullable = false)
    private Integer serving;

    @Column(name = "reg_time")
    private LocalDateTime regTime;

    @Column(name = "mod_time")
    private LocalDateTime modTime;

    @Column(name = "is_deleted", nullable = false)
    private StatusType statusType;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "rep_img")
    private String repImg;

    @OneToMany(fetch = LAZY, mappedBy = "recipe")
    private List<RecipeStep> steps = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "recipe")
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "recipe")
    private List<RecipeTag> tags = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "recipe")
    private List<RecipeLike> likes = new ArrayList<>();

    @OneToMany(fetch = LAZY, mappedBy = "recipe")
    private List<RecipeBookmark> bookmarks = new ArrayList<>();

    @Builder
    public Recipe(User user, String title, String content,Integer cookingTime, Integer serving, String category, String repImg) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.cookingTime = cookingTime;
        this.serving = serving;
        this.category = category;
        this.repImg = repImg;
        this.statusType = StatusType.N;
        this.regTime = LocalDateTime.now();
    }

    public void updateRecipe(ReqPutRecipeDto updateDto, String repImg) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
        this.cookingTime = updateDto.getCookingTime();
        this.serving = updateDto.getServing();
        this.category = updateDto.getCategory();
        this.repImg = repImg;
        this.modTime = LocalDateTime.now();
    }

    public void delete() {
        this.statusType = StatusType.Y;
    }
}
