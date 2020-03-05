/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Recipe.RecipeBuilder;

/**
 *
 * @author Adrian Klasson
 */
@NotNull
public final class Recipe implements Rebuildable<RecipeBuilder> {

    private Integer id;
    
    @NotNull
    private Integer userId;
    
    private Integer likes;
    
    @NotEmpty
    private String name;
    
    private String posterUsername;
    
    @NotEmpty
    private String image;
    
    @NotEmpty
    private String description;
    
    @NotEmpty
    private List<Category> categories;
    
    @NotEmpty
    private List<Ingredient> ingredients;
    
    @NotEmpty
    private List<Instruction> instructions;
    
    private List<Comment> comments;
    
    private List<Reply> replies;

    public Recipe() {
    }

    public Recipe(RecipeBuilder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.likes = builder.likes;
        this.name = builder.name;
        this.posterUsername = builder.posterUsername;
        this.image = builder.image;
        this.description = builder.description;
        this.categories = builder.categories;
        this.ingredients = builder.ingredients;
        this.instructions = builder.instructions;
        this.comments = builder.comments;
        this.replies = builder.replies;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosterUsername(String posterUsername) {
        this.posterUsername = posterUsername;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getLikes() {
        return likes;
    }

    public String getName() {
        return name;
    }

    public String getPosterUsername() {
        return posterUsername;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    @Override
    public RecipeBuilder rebuild() {
        return new RecipeBuilder()
                .categories(categories)
                .comments(comments)
                .description(description)
                .id(id)
                .image(image)
                .ingredients(ingredients)
                .instructions(instructions)
                .likes(likes)
                .name(name)
                .posterUsername(posterUsername)
                .replies(replies);
    }

    @Override
    public String toString() {
        return "Recipe{" + "id=" + id + ", userId=" + userId + ", likes=" + likes + ", name=" + name + ", posterUsername=" + posterUsername + ", image=" + image + ", description=" + description + ", categories=" + categories + ", ingredients=" + ingredients + ", instructions=" + instructions + ", comments=" + comments + ", replies=" + replies + '}';
    }

    public static final class RecipeBuilder {

        private Integer id;
        private Integer userId;
        private Integer likes;
        private String name;
        private String posterUsername;
        private String image;
        private String description;
        private List<Category> categories;
        private List<Ingredient> ingredients;
        private List<Instruction> instructions;
        private List<Comment> comments;
        private List<Reply> replies;

        public RecipeBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public RecipeBuilder likes(Integer likes) {
            this.likes = likes;
            return this;
        }

        public RecipeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RecipeBuilder posterUsername(String posterUsername) {
            this.posterUsername = posterUsername;
            return this;
        }

        public RecipeBuilder image(String image) {
            this.image = image;
            return this;
        }

        public RecipeBuilder description(String description) {
            this.description = description;
            return this;
        }

        public RecipeBuilder categories(List<Category> categories) {
            this.categories = categories;
            return this;
        }

        public RecipeBuilder ingredients(List<Ingredient> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeBuilder instructions(List<Instruction> instructions) {
            this.instructions = instructions;
            return this;
        }

        public RecipeBuilder comments(List<Comment> comments) {
            this.comments = comments;
            return this;
        }

        public RecipeBuilder replies(List<Reply> replies) {
            this.replies = replies;
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }
}
