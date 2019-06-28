package org.mengyun.tcctransaction.sample.order.domain;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlTest {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://10.10.2.244:3306/TCC_ORD?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        String username = "root";
        String password = "123456";

        String querySql="SELECT\n" +
                "        PRODUCT_ID,\n" +
                "        SHOP_ID,\n" +
                "        PRODUCT_NAME,\n" +
                "        PRICE\n" +
                "        FROM\n" +
                "        ORD_PRODUCT where SHOP_ID = 1";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(querySql);
            while (rs.next()) {
                Integer productId = rs.getInt("PRODUCT_ID");
                Integer shopId = rs.getInt("SHOP_ID");
                String productName = rs.getString("PRODUCT_NAME");
                BigDecimal price = rs.getBigDecimal("PRICE");
                System.out.println("productId: " + productId + "shopId: " + shopId + "productName: " + productName + "price: " + price);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e) {

            }
        }

    }

}
