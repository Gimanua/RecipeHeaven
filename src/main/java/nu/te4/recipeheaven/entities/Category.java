package nu.te4.recipeheaven.entities;

import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Category.CategoryBuilder;

/**
 *
 * @author Adrian Klasson
 */
public final class Category implements Rebuildable<CategoryBuilder> {
    private Integer categoryId;
    private String name;

    public Category(){
        
    }
    
    public Category(CategoryBuilder builder) {
        this.categoryId = builder.categoryId;
        this.name = builder.name;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public CategoryBuilder rebuild() {
        return new CategoryBuilder()
                .categoryId(categoryId)
                .name(name);
    }
    
    public static final class CategoryBuilder {
        private Integer categoryId;
        private String name;
        
        public CategoryBuilder categoryId(Integer categoryId){
            this.categoryId = categoryId;
            return this;
        }
        
        public CategoryBuilder name(String name){
            this.name = name;
            return this;
        }
        
        public Category build(){
            return new Category(this);
        }
    }
}
