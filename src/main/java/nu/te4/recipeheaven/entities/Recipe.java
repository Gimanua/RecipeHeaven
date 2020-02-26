/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

import java.util.List;
import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Recipe.RecipeBuilder;

/**
 *
 * @author Adrian Klasson
 */
public final class Recipe implements Rebuildable<RecipeBuilder>{

    private final Integer id;
    private final Integer likes;
    private final String name;
    private final String posterUsername;
    private final String image;
    private final String description;
    private final List<Category> categories;
    private final List<Ingredient> ingredients;
    private final List<Instruction> instructions;
    private final List<Comment> comments;
    private final List<Reply> replies;

    private Recipe(RecipeBuilder builder) {
        this.id = builder.id;
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

    public Integer getId() {
        return id;
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

    public static final class RecipeBuilder {

        private final Integer id;
        private final Integer likes;
        private final String name;
        private final String posterUsername;
        private final String image;
        private final String description;
        private final List<Category> categories;
        private final List<Ingredient> ingredients;
        private final List<Instruction> instructions;
        private final List<Comment> comments;
        private final List<Reply> replies;

        public RecipeBuilder() {
            this.id = null;
            this.likes = null;
            this.name = null;
            this.posterUsername = null;
            this.image = null;
            this.description = null;
            this.categories = null;
            this.ingredients = null;
            this.instructions = null;
            this.comments = null;
            this.replies = null;
        }

        private RecipeBuilder(Integer id, Integer likes, String name, String posterUsername, String image, String description, List<Category> categories, List<Ingredient> ingredients, List<Instruction> instructions, List<Comment> comments, List<Reply> replies) {
            this.id = id;
            this.likes = likes;
            this.name = name;
            this.posterUsername = posterUsername;
            this.image = image;
            this.description = description;
            this.categories = categories;
            this.ingredients = ingredients;
            this.instructions = instructions;
            this.comments = comments;
            this.replies = replies;
        }
        
        public RecipeBuilder id(Integer id){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder likes(Integer likes){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder name(String name){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder posterUsername(String posterUsername){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder image(String image){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder description(String description){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder categories(List<Category> categories){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder ingredients(List<Ingredient> ingredients){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder instructions(List<Instruction> instructions){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder comments(List<Comment> comments){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public RecipeBuilder replies(List<Reply> replies){
            return new RecipeBuilder(id, likes, name, posterUsername, image, description, categories, ingredients, instructions, comments, replies);
        }
        
        public Recipe build(){
            return new Recipe(this);
        }
    }
}
