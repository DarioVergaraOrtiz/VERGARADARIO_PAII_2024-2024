package Main;

import java.sql.Connection;
import java.util.List;

import Modelo.Alumno;
import Modelo.AlumnoDAO;

public class Principal {

	public static void main(String[] args) {
		
		Connection conn = Conexion.Database.connect();
        if (conn != null) {
        	
            System.out.println("Conexión establecida.");
            
        } else {
        	
            System.out.println("No se pudo establecer la conexión.");
        }
        
        
        AlumnoDAO alumnoDAO = new AlumnoDAO(conn);
        
        
        
        
        Alumno eliminarAlumno = alumnoDAO.get(1);
        
        alumnoDAO.delete(eliminarAlumno);

       
        List<Alumno> alumnos = alumnoDAO.getAll();
        for (Alumno alumno : alumnos) {
            System.out.println(alumno.getId() + " " + alumno.getName() + " " + alumno.getLastname() + ", " + alumno.getAge());
        }


	}

}
