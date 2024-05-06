package Modelo;

import java.sql.Time;

public class Horario {
	
	private int id_mat;
    private int id_alum;
    private int id_profesor;
    private Time hora_inicio;
    private Time hora_fin;
    private String dia;
    
    
	public int getId_mat() {
		return id_mat;
	}
	public void setId_mat(int id_mat) {
		this.id_mat = id_mat;
	}
	public int getId_alum() {
		return id_alum;
	}
	public void setId_alum(int id_alum) {
		this.id_alum = id_alum;
	}
	public int getId_profesor() {
		return id_profesor;
	}
	public void setId_profesor(int id_profesor) {
		this.id_profesor = id_profesor;
	}
	public Time getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(Time hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public Time getHora_fin() {
		return hora_fin;
	}
	public void setHora_fin(Time hora_fin) {
		this.hora_fin = hora_fin;
	}
	public String isDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
    
    

}
