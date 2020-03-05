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
public class ReportedRecipe {

    private int recipeId;
    private int reports;

    public ReportedRecipe() {
    }
    
    private ReportedRecipe(ReportedRecipeBuilder builder){
        this.recipeId = builder.recipeId;
        this.reports = builder.reports;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getReports() {
        return reports;
    }

    public void setReports(int reports) {
        this.reports = reports;
    }

    public static final class ReportedRecipeBuilder {

        private int recipeId;
        private int reports;
        
        public ReportedRecipeBuilder recipeId(int recipeId){
            this.recipeId = recipeId;
            return this;
        }
        
        public ReportedRecipeBuilder reports(int reports){
            this.reports = reports;
            return this;
        }
        
        public ReportedRecipe build(){
            return new ReportedRecipe(this);
        }
    }
}
