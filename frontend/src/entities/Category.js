export class Category{

    /**
     * 
     * @param {CategoryBuilder} categoryBuilder 
     */
    constructor(categoryBuilder){
        this.categoryId = categoryBuilder.categoryId;
        this.name = categoryBuilder.name;
    }
}

export class CategoryBuilder{

    setCategoryId(categoryId){
        this.categoryId = categoryId;
        return this;
    }

    setName(name){
        this.name = name;
        return this;
    }

    build(){
        return new Category(this);
    }
}