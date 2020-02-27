/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.resources;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Adrian Klasson
 */
public class RecipeResourceTest {
    
    public RecipeResourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getRecipe method, of class RecipeResource.
     */
    @Test
    public void testGetRecipe() {
        try {
            int id = 1;
            Connection mockedConnection = mock(Connection.class);
            
            /*
            CallableStatement stmt = connection.prepareCall("{call complete_recipe(?)}");
            stmt.setInt(1, id);
            stmt.execute()
            
            RecipeBuilder recipeBuilder = insertRecipeInfo(stmt.getResultSet());
                recipeData.getInt("recipe_id")
                recipeData.getInt("likes")
                recipeData.getString("name")
                recipeData.getString("poster_username")
                recipeData.getString("image")
                recipeData.getString("description")
            
            stmt.getMoreResults();
            */
            
            CallableStatement mockedCallableStatement = mock(CallableStatement.class);
            when(mockedCallableStatement.execute()).thenReturn(true);
            
            ResultSet mockedRecipeData = mock(ResultSet.class);
            when(mockedRecipeData.getInt("recipe_id")).thenReturn(id);
            when(mockedRecipeData.getInt("likes")).thenReturn(35);
            when(mockedRecipeData.getString("name")).thenReturn("Pannkakor");
            when(mockedRecipeData.getString("poster_username")).thenReturn("FoodLover");
            when(mockedRecipeData.getString("image")).thenReturn("/images/pannkakor.png");
            when(mockedRecipeData.getString("description")).thenReturn("Dessa små underverk är goda att äta.");
            
            /*
            List<Category> categories = categoryBean.getCategories(stmt.getResultSet());
                List<Category> categories = new LinkedList();
                while (categoryData.next()) {
                    CategoryBuilder categoryBuilder = new CategoryBuilder()
                    .categoryId(categoryData.getInt("category_id"))
                    .name(categoryData.getString("category_name"));
                    categories.add(categoryBuilder.build());
                }
                return categories;
            */
            ResultSet mockedCategoryData = mock(ResultSet.class);
            when(mockedCategoryData.getString("category_name")).thenReturn("Vegetarisk");
            
            
            //RecipeResource instance = new RecipeResource();
            //Response expResult = null;
            //Response result = instance.getRecipe(1);
            //assertEquals(expResult, result);
            // TODO review the generated test code and remove the default call to fail.
            
        } catch (SQLException ex) {
            fail("The test case is a prototype.");
        }
    }
    
}
