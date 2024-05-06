package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Interfaz.DAO;

public class MateriaDAO implements DAO<Materia>{

	private Connection conn;

    public MateriaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Materia get(int id) {
        Materia materia = new Materia();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM materia WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                materia.setId(resultSet.getInt("id"));
                materia.setName(resultSet.getString("name"));
                materia.setDescripcion(resultSet.getString("descripcion"));
                materia.setLevel(resultSet.getInt("level"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materia;
    }

    @Override
    public List<Materia> getAll() {
        List<Materia> materias = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM materia");

            while (resultSet.next()) {
                Materia materia = new Materia();
                materia.setId(resultSet.getInt("id"));
                materia.setName(resultSet.getString("name"));
                materias.add(materia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    @Override
    public void save(Materia materia) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO materia (name, descripcion, level) VALUES (?, ?, ?)");
            preparedStatement.setString(1, materia.getName());
            preparedStatement.setString(2, materia.getDescripcion());
            preparedStatement.setInt(3, materia.getLevel());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Materia materia) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE materia SET name = ?, descripcion = ?, level = ? WHERE id = ?");
            preparedStatement.setString(1, materia.getName());
            preparedStatement.setString(2, materia.getDescripcion());
            preparedStatement.setInt(3, materia.getLevel());
            preparedStatement.setInt(4, materia.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Materia materia) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM materia WHERE id = ?");
            preparedStatement.setInt(1, materia.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
