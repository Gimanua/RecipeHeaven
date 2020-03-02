/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Ingredient.IngredientBuilder;

/**
 *
 * @author Adrian Klasson
 */
public final class Ingredient implements Rebuildable<IngredientBuilder>{

    private Integer ingredientId;
    private Double amount;
    private String unitAbbreviation;
    private String unitName;
    private String name;

    public Ingredient(){
        
    }
    
    public Ingredient(IngredientBuilder builder) {
        this.ingredientId = builder.ingredientId;
        this.amount = builder.amount;
        this.unitAbbreviation = builder.unitAbbreviation;
        this.unitName = builder.unitName;
        this.name = builder.name;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUnitAbbreviation(String unitAbbreviation) {
        this.unitAbbreviation = unitAbbreviation;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getUnitAbbreviation() {
        return unitAbbreviation;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getName() {
        return name;
    }

    @Override
    public IngredientBuilder rebuild() {
        return new IngredientBuilder()
                .amount(amount)
                .ingredientId(ingredientId)
                .name(name)
                .unitAbbreviation(unitAbbreviation)
                .unitName(unitName);
    }

    public static final class IngredientBuilder {

        private Integer ingredientId;
        private Double amount;
        private String unitAbbreviation;
        private String unitName;
        private String name;
        
        public IngredientBuilder ingredientId(Integer ingredientId){
            this.ingredientId = ingredientId;
            return this;
        }
        
        public IngredientBuilder amount(Double amount){
            this.amount = amount;
            return this;
        }
        
        public IngredientBuilder unitAbbreviation(String unitAbbreviation){
            this.unitAbbreviation = unitAbbreviation;
            return this;
        }
        
        public IngredientBuilder unitName(String unitName){
            this.unitName = unitName;
            return this;
        }
        
        public IngredientBuilder name(String name){
            this.name = name;
            return this;
        }
        
        public Ingredient build(){
            return new Ingredient(this);
        }
    }
}
