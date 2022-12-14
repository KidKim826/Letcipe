package com.ssafy.letcipe.domain.recipeBookmark;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.letcipe.domain.recipe.Recipe;
import com.ssafy.letcipe.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "bookmark_constraint",
                columnNames = {"recipe_id", "user_id"}
        )
})
public class RecipeBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(targetEntity = Recipe.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    @JsonIgnore
    private Recipe recipe;
    @Builder
    public RecipeBookmark(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }
}
