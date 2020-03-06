export class Recipe{

    /**
     * 
     * @param {RecipeBuilder} recipeBuilder 
     */
    constructor(recipeBuilder){
        this.id = recipeBuilder.id;
        this.userId = recipeBuilder.userId;
        this.likes = recipeBuilder.likes;
        this.name = recipeBuilder.name;
        this.posterUsername = recipeBuilder.posterUsername;
        this.image = recipeBuilder.image;
        this.description = recipeBuilder.description;
        this.categories = recipeBuilder.categories;
        this.ingredients = recipeBuilder.ingredients;
        this.instructions = recipeBuilder.instructions;
        this.comments = recipeBuilder.comments;
        this.replies = recipeBuilder.replies;
    }
}

export class RecipeBuilder{

    setId(id) {
        this.id = id;
        return this;
    }

    setUserId(userId) {
        this.userId = userId;
        return this;
    }

    setLikes(likes) {
        this.likes = likes;
        return this;
    }

    setName(name) {
        this.name = name;
        return this;
    }

    setPosterUsername(posterUsername) {
        this.posterUsername = posterUsername;
        return this;
    }

    setImage(image) {
        this.image = image;
        return this;
    }

    setDescription(description) {
        this.description = description;
        return this;
    }

    setCategories(categories) {
        this.categories = categories;
        return this;
    }

    setIngredients(ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    setInstructions(instructions) {
        this.instructions = instructions;
        return this;
    }

    setComments(comments) {
        this.comments = comments;
        return this;
    }

    setReplies(replies) {
        this.replies = replies;
        return this;
    }

    build(){
        return new Recipe(this);
    }
    
}