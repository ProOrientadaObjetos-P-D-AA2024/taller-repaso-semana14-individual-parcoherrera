package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    private Connection conn;
    
    public void establecerConexion(){
        try{
            String url = "jdbc:sqlite:db/dbTest1.db";
            conn = DriverManager.getConnection(url);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public Connection obtenerConexion(){
        return conn;
    }
}
