package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Interfaz.DAO;

public class ProfesorDAO implements DAO<Profesor>{
	
	private Connection conn;

    public ProfesorDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Profesor get(int id) {
        Profesor profesor = new Profesor();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM profesor WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                profesor.setId(resultSet.getInt("id"));
                profesor.setName(resultSet.getString("name"));
                profesor.setLastname(resultSet.getString("lastname"));
                profesor.setAge(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesor;
    }

    @Override
    public List<Profesor> getAll() {
        List<Profesor> profesores = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM profesor");

            while (resultSet.next()) {
                Profesor profesor = new Profesor();
                profesor.setId(resultSet.getInt("id"));
                profesor.setName(resultSet.getString("name"));
                profesor.setLastname(resultSet.getString("lastname"));
                profesor.setAge(resultSet.getInt("age"));
                profesores.add(profesor);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profesores;
    }

    @Override
    public void save(Profesor profesor) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO profesor (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, profesor.getName());
            preparedStatement.setString(2, profesor.getLastname());
            preparedStatement.setInt(3, profesor.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Profesor profesor) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE profesor SET name = ?, lastname = ?, age = ? WHERE id = ?");
            preparedStatement.setString(1, profesor.getName());
            preparedStatement.setString(2, profesor.getLastname());
            preparedStatement.setInt(3, profesor.getAge());
            preparedStatement.setInt(4, profesor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Profesor profesor) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM profesor WHERE id = ?");
            preparedStatement.setInt(1, profesor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
