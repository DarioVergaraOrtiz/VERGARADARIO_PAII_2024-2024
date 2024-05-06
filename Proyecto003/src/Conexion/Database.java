package Conexion;

import java.sql.*;

public class Database {
	
    private static final String url = "jdbc:postgresql://localhost:5432/Instituto";
    private static final String user = "postgres";
    private static final String password = "Pera1805";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a la base de datos PostgreSQL servidor con éxito.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
}

