package dal;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import java.sql.*;
import model.Category;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<Product> findAll() {
        List<Product> listFound = new ArrayList<>();
        // connect with db
        connection = getConnection();
        // cbi cau lenh sql
        String sql = "select p.*,c.name AS categoryName,c.description from Product p "
                + "left join Category c on p.categoryId = c.id";
        // tao doi tuong PreparedStatement
        try {
            statement = connection.prepareStatement(sql);
            // Set parameter(optional) 
            // Thuc thi cau lenh
            resultSet = statement.executeQuery();

            // Tra ve ket qua
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName"), resultSet.getString(9));
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                String image = resultSet.getString("image").trim();
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String description = resultSet.getString("description").trim();
                Product product = new Product(id, name, image, quantity, price, description, category);
                listFound.add(product);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listFound;
    }

    public List<Product> searchProductByName(String pName) {
        List<Product> listFound = new ArrayList<>();
        // connect with db
        connection = getConnection();
        // cbi cau lenh sql
        String sql = "select p.*,c.name AS categoryName,c.description from Product p "
                + "left join Category c on p.categoryId = c.id where p.name like ?";
        // tao doi tuong PreparedStatement
        try {
            statement = connection.prepareStatement(sql);
            // Set parameter(optional) 
            // Thuc thi cau lenh
            statement.setString(1, "%" + pName + "%");
            resultSet = statement.executeQuery();

            // Tra ve ket qua
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName"), resultSet.getString(9));
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                String image = resultSet.getString("image").trim();
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String description = resultSet.getString("description").trim();
                Product product = new Product(id, name, image, quantity, price, description, category);
                listFound.add(product);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return listFound;
    }

    public Product findById(Product product) {
        connection = getConnection();
        Product productFound = null;
        String sql = "SELECT p.id, p.name, p.image, p.quantity, p.price, p.description, p.categoryID, c.name AS categoryName,c.description\n"
                + "                     FROM [dbo].[Product] p\n"
                + "                     LEFT JOIN [dbo].[Category] c ON p.categoryID = c.id \n"
                + "                     WHERE p.id = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, product.getId());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Category category = new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName"), resultSet.getString(9));
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                String image = resultSet.getString("image").trim();
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String description = resultSet.getString("description").trim();
                productFound = new Product(id, name, image, quantity, price, description, category);
            }

        } catch (Exception e) {
        }
        return productFound;
    }

    public List<Product> findByCategory(String categoryId) {
        List<Product> listFound = new ArrayList<>();
        connection = getConnection();

        String sql = "SELECT p.id, p.name, p.image, p.quantity, p.price, p.description, p.categoryID, \n"
                + "c.name AS categoryName, c.description AS categoryDescription\n"
                + "FROM [dbo].[Product] p\n"
                + "LEFT JOIN [dbo].[Category] c ON p.categoryID = c.id\n"
                + "WHERE p.categoryID = ?";

        // tao doi tuong PreparedStatement
        try {
            statement = connection.prepareStatement(sql);
            // Set parameter(optional) 
            statement.setInt(1, Integer.parseInt(categoryId));
            // Thuc thi cau lenh
            resultSet = statement.executeQuery();

            // Tra ve ket qua
            while (resultSet.next()) {
                Category category = new Category(resultSet.getInt("categoryId"), resultSet.getString("categoryName"), resultSet.getString("categoryDescription"));
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name").trim();
                String image = resultSet.getString("image").trim();
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String description = resultSet.getString("description").trim();
                Product product = new Product(id, name, image, quantity, price, description, category);
                listFound.add(product);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {

        }
        return listFound;

    }

    public static void main(String[] args) {
        for (Product product : new ProductDAO().searchProductByName("am")) {
            System.out.println(product);
        }
    }

    public Category findCategoryById(int categoryId) {
        String sql = "SELECT * FROM Category WHERE id = ?";
        Category category = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                category = new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public void add(Product product) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[image]\n"
                + "           ,[quantity]\n"
                + "           ,[price]\n"
                + "           ,[description]\n"
                + "           ,[categoryID])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, product.getName());
            statement.setString(2, product.getImage());
            statement.setInt(3, product.getQuantity());
            statement.setFloat(4, product.getPrice());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getCategory().getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
