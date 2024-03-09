package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class CategoryDAO extends DBContext{
    public List<Category> findAll() {
        List<Category> listFound = new ArrayList<>();
        // connect with db
        connection = getConnection();
        // cbi cau lenh sql
        String sql = "select * from Category";
        // tao doi tuong PreparedStatement
        try {
            statement = connection.prepareStatement(sql);
            // Set parameter(optional) 
            // Thuc thi cau lenh
            resultSet = statement.executeQuery();

            // Tra ve ket qua
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                String description = resultSet.getString("description");
                Category category = new Category(id, name, description);
                
                listFound.add(category);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listFound;
    }
    
    
    public static void main(String[] args) {
        for (Category category : new CategoryDAO().findAll()) {
            System.out.println(category);
        }
    }
    
    
}
