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

    private final Integer orderIndex;
    private final String description;

    private Instruction(InstructionBuilder builder) {
        this.orderIndex = builder.orderIndex;
        this.description = builder.description;
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

    public static final class InstructionBuilder {

        private final Integer orderIndex;
        private final String description;

        public InstructionBuilder() {
            this.orderIndex = null;
            this.description = null;
        }

        private InstructionBuilder(Integer orderIndex, String description) {
            this.orderIndex = orderIndex;
            this.description = description;
        }
        
        public InstructionBuilder orderIndex(Integer orderIndex){
            return new InstructionBuilder(orderIndex, description);
        }
        
        public InstructionBuilder description(String description){
            return new InstructionBuilder(orderIndex, description);
        }
        
        public Instruction build(){
            return new Instruction(this);
        }
    }
}

