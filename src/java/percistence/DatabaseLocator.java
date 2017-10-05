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
            //Connection conn = DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net/sql10196618","sql10196618","uXr9APNuSN");
            Connection conn = DriverManager.getConnection("jdbc:mysql://172.18.10.31/11385684739", "11385684739", "123456");
            return conn;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
