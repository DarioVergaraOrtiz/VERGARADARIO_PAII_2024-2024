package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import Interfaz.DAO;
	
	public class AlumnoDAO implements DAO<Alumno> {
	    private Connection conn;

	    public AlumnoDAO(Connection conn) {
	        this.conn = conn;
	    }

	    @Override
	    public Alumno get(int id) {
	        Alumno alumno = new Alumno();
	        try {
	            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM alumno WHERE id = ?");
	            preparedStatement.setInt(1, id);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                alumno.setId(resultSet.getInt("id"));
	                alumno.setName(resultSet.getString("name"));
	                alumno.setLastname(resultSet.getString("lastname"));
	                alumno.setAge(resultSet.getInt("age"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return alumno;
	    }

	    @Override
	    public List<Alumno> getAll() {
	        List<Alumno> alumnos = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            ResultSet resultSet = statement.executeQuery("SELECT * FROM alumno");

	            while (resultSet.next()) {
	                Alumno alumno = new Alumno();
	                alumno.setId(resultSet.getInt("id"));
	                alumno.setName(resultSet.getString("name"));
	                alumno.setLastname(resultSet.getString("lastname"));
	                alumno.setAge(resultSet.getInt("age"));
	                alumnos.add(alumno);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return alumnos;
	    }

	    @Override
	    public void save(Alumno alumno) {
	        try {
	            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO alumno (name, lastname, age) VALUES (?, ?, ?)");
	            preparedStatement.setString(1, alumno.getName());
	            preparedStatement.setString(2, alumno.getLastname());
	            preparedStatement.setInt(3, alumno.getAge());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void update(Alumno alumno) {
	        try {
	            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE alumno SET name = ?, lastname = ?, age = ? WHERE id = ?");
	            preparedStatement.setString(1, alumno.getName());
	            preparedStatement.setString(2, alumno.getLastname());
	            preparedStatement.setInt(3, alumno.getAge());
	            preparedStatement.setInt(4, alumno.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(Alumno alumno) {
	        try {
	            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM alumno WHERE id = ?");
	            preparedStatement.setInt(1, alumno.getId());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}

	


