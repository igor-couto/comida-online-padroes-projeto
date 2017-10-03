package percistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLocator {
    
    private static DatabaseLocator instance = new DatabaseLocator();
    
    private DatabaseLocator(){}
    
    public static DatabaseLocator getInstance(){
        return instance;
    }
    
    public Connection getConnection(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10196618","sql10196618","uXr9APNuSN");
            return conn;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
