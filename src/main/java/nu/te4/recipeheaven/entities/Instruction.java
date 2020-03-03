/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.entities;

import nu.te4.recipeheaven.building.Rebuildable;
import nu.te4.recipeheaven.entities.Instruction.InstructionBuilder;

/**
 *
 * @author Adrian Klasson
 */
public final class Instruction implements Rebuildable<InstructionBuilder>{

    private Integer orderIndex;
    private String description;

    public Instruction(){
        
    }
    
    public Instruction(InstructionBuilder builder) {
        this.orderIndex = builder.orderIndex;
        this.description = builder.description;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public InstructionBuilder rebuild() {
        return new InstructionBuilder()
                .description(description)
                .orderIndex(orderIndex);
    }

    @Override
    public String toString() {
        return "Instruction{" + "orderIndex=" + orderIndex + ", description=" + description + '}';
    }

    public static final class InstructionBuilder {

        private Integer orderIndex;
        private String description;
        
        public InstructionBuilder orderIndex(Integer orderIndex){
            this.orderIndex = orderIndex;
            return this;
        }
        
        public InstructionBuilder description(String description){
            this.description = description;
            return this;
        }
        
        public Instruction build(){
            return new Instruction(this);
        }
    }
}

