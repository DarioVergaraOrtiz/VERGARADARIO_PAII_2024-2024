package Principal;

/**
 * @author Dario Vergara
 * Titulo: Inversión de dependencias
 */

public class Main {
	public static void main(String[] args) {
		DibujoRapido a = new DibujoRapido(new Triangulo());
		DibujoRapido b = new DibujoRapido(new Cuadrado());
		DibujoRapido c = new DibujoRapido(new Circulo());
	}
}
