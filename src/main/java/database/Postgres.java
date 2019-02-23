package database;

import utilities.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Postgres {

    static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
                    Util.getSQLPWDString());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static Connection getApolloConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9003/apollo", "apollosuperuser",
                    "Serverg0d!");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static Connection getDPAConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:9003/postgres", "apollouser",
                    "Serverg0d!");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTask() {
        String task = "";
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT value FROM public.work where key='task';");
            rs.next();
            task = rs.getString("value");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    public static String getValue(String table, String key) {
        String value = "";
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT value FROM public." + table + " where key='" + key + "';");
//            ResultSet rs = stmt.executeQuery( "SELECT * FROM public." + table + ";");
            rs.next();
            value = rs.getString("value");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void insertValue(String key, String value, String table) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement
                         = connection.prepareStatement("INSERT into public." + table + " values (?, ?);")) {
                preparedStatement.setString(1, key);
                preparedStatement.setString(2, value);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String key, String table) {
//        try (Connection connection = getConnection()) {
//            try (PreparedStatement preparedStatement
//                         = connection.prepareStatement("DELETE from public." + table + "where key=""'"+ key +"'")) {
//                preparedStatement.setString(1, key);
////                preparedStatement.exe();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        if (args[0].equals("get")) {
            System.out.println(getValue(args[1], args[2]));
        }
        if (args[0].equals("insert")) {
            insertValue(args[1], args[2], args[3]);
        }
    }

    public void showGerman() {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM public.task where label='german';");
            int i = 0;
            System.out.println();
            while (rs.next()) {
                String value = rs.getString("value");
                System.out.println("\t" + ++i + ") " + value);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTask(String newTask) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("UPDATE public.work set value = ? where key='task';")) {
            preparedStatement.setString(1, newTask);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setValue(String table, String key, String value) {
        Connection connection = getConnection();
        try (PreparedStatement preparedStatement
                     = connection.prepareStatement("UPDATE public." + table + " set value = ? where key=?;")) {
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, key);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAdminPassword() {
        try (Connection connection = getDPAConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM apollo.abstractcredential where label='german';");
            int i = 0;
            System.out.println();
            while (rs.next()) {
                String value = rs.getString("value");
                System.out.println("\t" + ++i + ") " + value);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
