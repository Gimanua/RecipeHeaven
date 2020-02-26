/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.entities.Instruction;
import nu.te4.recipeheaven.entities.Instruction.InstructionBuilder;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class InstructionBean {
    
    public List<Instruction> getInstructions(ResultSet instructionData) throws SQLException{
        List<Instruction> instructions = new LinkedList();
        while(instructionData.next()){
            InstructionBuilder builder = new InstructionBuilder()
                    .description(instructionData.getString("description"))
                    .orderIndex(instructionData.getInt("order_index"));
            instructions.add(builder.build());
        }
        return instructions;
    }
}
