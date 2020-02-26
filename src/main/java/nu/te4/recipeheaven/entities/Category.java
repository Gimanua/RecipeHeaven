package nu.te4.recipeheaven.entities;

import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Category.CategoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
public final class Category implements Rebuildable<CategoryBuilder> {
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

    @Override
    public CategoryBuilder rebuild() {
        return new CategoryBuilder()
                .categoryId(categoryId)
                .name(name);
    }
    
    public static final class CategoryBuilder {
        private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBuilder.class);
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
        
        public CategoryBuilder categoryId(Integer categoryId){
            LOGGER.debug("categoryId set to: {}", categoryId);
            return new CategoryBuilder(categoryId, name);
        }
        
        public CategoryBuilder name(String name){
            LOGGER.debug("name set to: {}", name);
            return new CategoryBuilder(categoryId, name);
        }
        
        public Category build(){
            if(name == null){
                throw new IllegalStateException("name must not be null");
            }
            
            return new Category(this);
        }
    }
}
