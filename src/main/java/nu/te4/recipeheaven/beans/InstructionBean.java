/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import nu.te4.recipeheaven.ConnectionFactory;
import nu.te4.recipeheaven.entities.Ingredient;
import nu.te4.recipeheaven.entities.Instruction;
import nu.te4.recipeheaven.entities.Instruction.InstructionBuilder;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class InstructionBean {

    public List<Instruction> getInstructions(ResultSet instructionData) throws SQLException {
        List<Instruction> instructions = new LinkedList();
        while (instructionData.next()) {
            InstructionBuilder builder = new InstructionBuilder()
                    .description(instructionData.getString("description"))
                    .orderIndex(instructionData.getInt("order_index"));
            instructions.add(builder.build());
        }
        return instructions;
    }

    /**
     * Inserts some instructions for a recipe.
     *
     * @param instructions The instructions to insert.
     * @param recipeId The Id of the recipe the instructions are for.
     */
    public void insertInstructions(List<Instruction> instructions, int recipeId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        String sql = "INSERT INTO instructions (recipe_id, order_index, description) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (Instruction instruction : instructions) {
            stmt.setInt(1, recipeId);
            stmt.setInt(2, instruction.getOrderIndex());
            stmt.setString(3, instruction.getDescription());
            stmt.executeUpdate();
        }
    }
}
