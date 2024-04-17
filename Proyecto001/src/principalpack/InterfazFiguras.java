package principalpack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Dario Vergara 
 * Título: Interfaz gráfica - figuras
 */

//Clase InterfazGrafica que hereda de JFrame para crear una interfaz gráfica de usuario

public class InterfazFiguras extends JFrame {

	private static final long serialVersionUID = 1L; // Identificador único para la serialización
	private GLCanvas glcanvas; // Componente de alto nivel que utiliza OpenGL para dibujar
	private Shape shapeToDraw = null; // Variable para almacenar la forma que se va a dibujar

	// Enumeración para las formas que se pueden dibujar

	enum Shape {
		CUADRADO, TRIANGULO, CIRCULO
	}

	// Método principal que lanza la aplicación

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazFiguras frame = new InterfazFiguras(); // Crea una instancia de la interfaz gráfica
					frame.setVisible(true); // Hace visible la interfaz gráfica
				} catch (Exception e) {
					e.printStackTrace(); // Imprime la traza de la pila del error si ocurre uno
				}
			}
		});
	}

	// Constructor de la clase InterfazGrafica

	public InterfazFiguras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura la operación de cierre por defecto
		setBounds(100, 100, 458, 388); // Establece el tamaño y la posición de la ventana

		GLProfile glprofile = GLProfile.getDefault(); // Obtiene el perfil de GL por defecto
		GLCapabilities glcapabilities = new GLCapabilities(glprofile); // Obtiene las capacidades de GL del perfil
		glcanvas = new GLCanvas(glcapabilities); // Crea un nuevo GLCanvas con las capacidades de GL

		// Añade un GLEventListener al GLCanvas
		glcanvas.addGLEventListener(new GLEventListener() {
			// Método que se llama cuando el GLAutoDrawable cambia de tamaño
			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
				GL2 gl = drawable.getGL().getGL2(); // Obtiene el objeto GL2 del GLAutoDrawable
				gl.glViewport(0, 0, width, height); // Establece la ventana de visualización
			}

			// Método que se llama para iniciar el GLAutoDrawable
			@Override
			public void init(GLAutoDrawable drawable) {
				GL2 gl = drawable.getGL().getGL2(); // Obtiene el objeto GL2 del GLAutoDrawable
				gl.glClearColor(1, 1, 1, 1); // Establece el color de fondo
			}

			// Método que se llama para destruir el GLAutoDrawable
			@Override
			public void dispose(GLAutoDrawable drawable) {
			}

			// Método que se llama para dibujar el GLAutoDrawable

			@Override
			public void display(GLAutoDrawable drawable) {
				GL2 gl = drawable.getGL().getGL2(); // Obtiene el objeto GL2 del GLAutoDrawable
				gl.glClear(GL2.GL_COLOR_BUFFER_BIT); // Limpia el buffer de color

				// Si hay una forma para dibujar, dibuja la forma correspondiente

				if (shapeToDraw != null) {
					switch (shapeToDraw) {
					case CUADRADO: // Si la forma es un cuadrado
						gl.glColor3f(1, 0, 0); // Establece el color a rojo
						gl.glRectf(-0.5f, -0.5f, 0.5f, 0.5f); // Dibuja un cuadrado
						break;
					case TRIANGULO: // Si la forma es un triángulo
						gl.glColor3f(0, 1, 0); // Establece el color a verde
						gl.glBegin(GL2.GL_TRIANGLES); // Comienza a dibujar un triángulo
						gl.glVertex2f(-0.5f, -0.5f); // Primer vértice del triángulo
						gl.glVertex2f(0.5f, -0.5f); // Segundo vértice del triángulo
						gl.glVertex2f(0, 0.5f); // Tercer vértice del triángulo
						gl.glEnd(); // Termina de dibujar el triángulo
						break;
					case CIRCULO: // Si la forma es un círculo
						double scale = 0.5; // Reduce el tamaño del círculo a la mitad
						gl.glColor3f(0, 0, 1); // Establece el color a azul
						gl.glBegin(GL2.GL_POLYGON); // Comienza a dibujar un polígono (círculo)
						for (int i = 0; i <= 300; i++) { // Para cada punto en el círculo
							double angle = 2 * Math.PI * i / 300; // Calcula el ángulo del punto
							double x = scale * Math.cos(angle); // Calcula la coordenada x del punto
							double y = scale * Math.sin(angle); // Calcula la coordenada y del punto
							gl.glVertex2d(x, y); // Dibuja el punto en el círculo
						}
						gl.glEnd(); // Termina de dibujar el círculo
						break;
					}
					shapeToDraw = null; // Resetea la forma a dibujar
				}
			}
		});

		JPanel buttonPanel = new JPanel(); // Crea un nuevo panel para los botones

		JButton squareButton = new JButton("Dibujar Cuadrado"); // Crea un nuevo botón para dibujar un cuadrado
		squareButton.addActionListener(new ActionListener() { // Añade un ActionListener al botón
			@Override
			public void actionPerformed(ActionEvent e) { // Método que se llama cuando se hace clic en el botón
				shapeToDraw = Shape.CUADRADO; // Establece la forma a dibujar a SQUARE
				glcanvas.display(); // Redibuja el GLCanvas
			}
		});

		JButton triangleButton = new JButton("Dibujar Triángulo"); // Crea un nuevo botón para dibujar un triángulo
		triangleButton.addActionListener(new ActionListener() { // Añade un ActionListener al botón
			@Override
			public void actionPerformed(ActionEvent e) { // Método que se llama cuando se hace clic en el botón
				shapeToDraw = Shape.TRIANGULO; // Establece la forma a dibujar a TRIANGLE
				glcanvas.display(); // Redibuja el GLCanvas
			}
		});

		JButton circleButton = new JButton("Dibujar Círculo"); // Crea un nuevo botón para dibujar un círculo
		circleButton.addActionListener(new ActionListener() { // Añade un ActionListener al botón
			@Override
			public void actionPerformed(ActionEvent e) { // Método que se llama cuando se hace clic en el botón
				shapeToDraw = Shape.CIRCULO; // Establece la forma a dibujar a CIRCLE
				glcanvas.display(); // Redibuja el GLCanvas
			}
		});

		buttonPanel.add(squareButton); // Añade el botón de cuadrado al panel de botones
		buttonPanel.add(triangleButton); // Añade el botón de triángulo al panel de botones
		buttonPanel.add(circleButton); // Añade el botón de círculo al panel de botones

		getContentPane().add(buttonPanel, BorderLayout.WEST); // Añade el panel de botones a la ventana en el lado oeste
		getContentPane().add(glcanvas, BorderLayout.CENTER); // Añade el GLCanvas a la ventana en el centro

		pack(); // Empaqueta la ventana
	}
}
