package org.ba.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBProvider {

    public static List<String> getPrinters() {
        try (Connection connection = connect()) {
            return getAvailablePrintersInDB(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connect() {

        String host = "localhost";
        String port = "3306";
        String dbName = "printer_db";
        String user = "root";
        String password = "ml74ac26";


        try {
            String url = String.format("jdbc:mariadb://%s:%s/%s", host, port, dbName);
            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> getAvailablePrintersInDB(Connection connection) {
        List<String> printers = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT printer_name, ip FROM printer")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                printers.add(rs.getString(1) + " (" + rs.getString(2) + ")");
            }
            return printers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(DBProvider.getPrinters());
    }
}
