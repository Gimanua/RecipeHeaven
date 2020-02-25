/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

/**
 *
 * @author Adrian Klasson
 */
public final class Category {
    private final Integer categoryId;
    private final String name;

    private Category(CategoryBuilder builder) {
        this.categoryId = builder.categoryId;
        this.name = builder.name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }
    
    public static final class CategoryBuilder{
        private final Integer categoryId;
        private final String name;
        
        public CategoryBuilder(){
            categoryId = null;
            name = null;
        }
        
        private CategoryBuilder(Integer categoryId, String name){
            this.categoryId = categoryId;
            this.name = name;
        }
        
        public CategoryBuilder id(Integer categoryId){
            return new CategoryBuilder(categoryId, name);
        }
        
        public CategoryBuilder name(String name){
            return new CategoryBuilder(categoryId, name);
        }
        
        public Category build(){
            return new Category(this);
        }
    }
}
