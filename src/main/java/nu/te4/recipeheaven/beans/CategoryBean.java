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
import nu.te4.recipeheaven.entities.Category;
import nu.te4.recipeheaven.entities.Category.CategoryBuilder;

/**
 *
 * @author giman
 */
@Stateless
public class CategoryBean {

    public List<Category> getCategories(ResultSet categoryData) throws SQLException {
        List<Category> categories = new LinkedList();
        while (categoryData.next()) {
            CategoryBuilder categoryBuilder = new CategoryBuilder()
                    .id(categoryData.getInt("category_id"))
                    .name(categoryData.getString("category_name"));
            categories.add(categoryBuilder.build());
        }
        return categories;
    }
}
