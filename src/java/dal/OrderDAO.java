package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Cart;
import model.Category;
import model.Item;
import model.OrderDetail;
import model.Orders;
import model.Product;

public class OrderDAO extends DBContext {

    public OrderDAO() {
        connection = getConnection();
    }

    public void addOrder(String name, String phone, String address, String note, Account a, Cart cart) {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        try {
            String sqlOrder = "INSERT INTO [dbo].[Order]\n"
                    + "           ([FullName]\n"
                    + "           ,[Phone]\n"
                    + "           ,[Address]\n"
                    + "           ,[Total]\n"
                    + "           ,[CreatAt]\n"
                    + "           ,[AccountID]\n"
                    + "           ,[Note])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sqlOrder);
            st.setString(1, name);
            st.setString(2, phone);
            st.setString(3, address);
            st.setDouble(4, cart.getTotalMoney());
            st.setTimestamp(5, timestamp);
            st.setInt(6, a.getId());
            st.setString(7, note);
            st.executeUpdate();

            //Lay ra id cua order vua add
            String sql1 = "select top 1 id from [Order] order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            //Add vao bang orderline
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getList()) {
                    String sql2 = "INSERT INTO [dbo].[OrderDetail]\n"
                            + "           ([quantity]\n"
                            + "           ,[price]\n"
                            + "           ,[ProductID]\n"
                            + "           ,[OrderID])\n"
                            + "     VALUES\n"
                            + "           (?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, i.getQuantity());
                    st2.setDouble(2, i.getPrice());
                    st2.setInt(3, i.getProduct().getId());
                    st2.setInt(4, oid);
                    st2.executeUpdate();
                }
            }

            //update so luong trong bang Product sau khi ban
            String sql3 = "update Product set quantity = quantity - ? "
                    + "where id = ?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            for (Item i : cart.getList()) {
                st3.setInt(1, i.getQuantity());
                st3.setInt(2, i.getProduct().getId());
                st3.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Orders> getOrders() {
        ArrayList<Orders> orders = new ArrayList<>();
        String sql = "SELECT  [id]\n"
                + "      ,[FullName]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[Total]\n"
                + "      ,[CreatAt]\n"
                + "      ,[AccountID]\n"
                + "      ,[Note]\n"
                + "  FROM [Order]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getFloat(5), rs.getString(6), rs.getString(7), rs.getString(8));
                orders.add(o);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<OrderDetail> getOrderDetail(String oid) {
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT  od.id\n"
                + "      ,od.quantity\n"
                + "      ,od.price\n"
                + "      ,[ProductID]\n"
                + "      ,[OrderID]\n"
                + "	  ,p.name\n"
                + "	  ,p.image\n"
                + "  FROM [FruitShop].[dbo].[OrderDetail] od\n"
                + "  JOIN Product p ON p.id = od.ProductID\n"
                + "  WHERE od.OrderID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, oid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail od = new OrderDetail(rs.getString(1), rs.getInt(2), rs.getFloat(3), rs.getString(4), rs.getString(5));
                Product p = new Product();
                p.setName(rs.getString(6));
                p.setImage(rs.getString(7));
                od.setP(p);
                orderDetails.add(od);
            }
            return orderDetails;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
