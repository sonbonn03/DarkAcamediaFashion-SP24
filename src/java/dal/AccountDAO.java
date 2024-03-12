/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public Account findAccFound(Account account) {
        connection = getConnection();
        String sql = "select * from Account\n"
                + "where username = ? and password = ?";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            resultSet = statement.executeQuery();

            if (resultSet.next()) {

                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setEmail(resultSet.getString("email"));
                account.setAddress(resultSet.getString("address"));
                account.setRoleId(resultSet.getInt("roleId"));

                return account;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void createAccount(Account account) {
        connection = getConnection();
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           ([username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           \n"
                + "           ,[roleId])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getEmail());
            statement.setInt(4, 1);
            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserExist(String username) {
        connection = getConnection();
        String sql = "SELECT  [username]\n"
                + "  FROM [FruitShop].[dbo].[Account] \n"
                + "  WHERE username = ? ";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;

    }

}
