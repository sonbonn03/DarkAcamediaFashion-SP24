/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Role;

/**
 *
 * @author Admin
 */
public class RoleDAO extends DBContext{
    
    public List<Role> findAll() {
        List<Role> listFound = new ArrayList<>();
        connection = getConnection();
        String sql = "select * from Role";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String RoleName = resultSet.getString("RoleName");
                Role role = new Role(id, RoleName);
                listFound.add(role);
            }
        } catch (Exception e) {
        }
        return listFound;
    }
}
