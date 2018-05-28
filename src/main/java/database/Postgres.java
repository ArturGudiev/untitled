package database;

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
                    "362044Sql");
            return connection;
        }
        catch (Exception e) {
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTask(){
        String task = "";
        try (Connection connection = getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT value FROM public.work where key='task';");
            rs.next();
            task =  rs.getString("value");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return task;
    }

    public static void main(String[] args) {

    }

    public void showGerman() {
        try (Connection connection = getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM public.task where label='german';" );
            int i = 0;
            System.out.println();
            while ( rs.next()) {
                String value = rs.getString("value");
                System.out.println("\t" + ++i + ") " +  value);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTask(String newTask) {
        Connection connection = getConnection();
        try(PreparedStatement preparedStatement
                    = connection.prepareStatement("UPDATE public.work set value = ? where key='task';")) {
            preparedStatement.setString(1, newTask);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setAdminPassword() {
        try (Connection connection = getDPAConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM apollo.abstractcredential where label='german';" );
            int i = 0;
            System.out.println();
            while ( rs.next()) {
                String value = rs.getString("value");
                System.out.println("\t" + ++i + ") " +  value);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
