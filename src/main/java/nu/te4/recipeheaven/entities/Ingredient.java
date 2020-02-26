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

    private final Integer ingredientId;
    private final Double amount;
    private final String unitAbbreviation;
    private final String unitName;
    private final String name;

    private Ingredient(IngredientBuilder builder) {
        this.ingredientId = builder.ingredientId;
        this.amount = builder.amount;
        this.unitAbbreviation = builder.unitAbbreviation;
        this.unitName = builder.unitName;
        this.name = builder.name;
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

        private final Integer ingredientId;
        private final Double amount;
        private final String unitAbbreviation;
        private final String unitName;
        private final String name;

        public IngredientBuilder(){
            this.ingredientId = null;
            this.amount = null;
            this.unitAbbreviation = null;
            this.unitName = null;
            this.name = null;
        }
        
        private IngredientBuilder(Integer ingredientId, Double amount, String unitAbbreviation, String unitName, String name) {
            this.ingredientId = ingredientId;
            this.amount = amount;
            this.unitAbbreviation = unitAbbreviation;
            this.unitName = unitName;
            this.name = name;
        }
        
        public IngredientBuilder ingredientId(Integer ingredientId){
            return new IngredientBuilder(ingredientId, amount, unitAbbreviation, unitName, name);
        }
        
        public IngredientBuilder amount(Double amount){
            return new IngredientBuilder(ingredientId, amount, unitAbbreviation, unitName, name);
        }
        
        public IngredientBuilder unitAbbreviation(String unitAbbreviation){
            return new IngredientBuilder(ingredientId, amount, unitAbbreviation, unitName, name);
        }
        
        public IngredientBuilder unitName(String unitName){
            return new IngredientBuilder(ingredientId, amount, unitAbbreviation, unitName, name);
        }
        
        public IngredientBuilder name(String name){
            return new IngredientBuilder(ingredientId, amount, unitAbbreviation, unitName, name);
        }
        
        public Ingredient build(){
            return new Ingredient(this);
        }
    }
}
