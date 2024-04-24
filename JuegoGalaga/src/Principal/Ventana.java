package Principal;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class Ventana extends JFrame {

	private JPanel contentPane;
	int[] marcianoX = { 120, 120, 160, 200, 200 };
	int[] marcianoY = { 160, 240, 200, 240, 160 };
	int dx = 2; // Cambio en x
	int dy = 2; // Cambio en y
	int trianguloX = 400;
    int trianguloY = 450;
    int limiteSuperior = 100; // El límite superior para el movimiento del marciano
    int limiteInferior = 300; // El límite inferior para el movimiento del marciano
    Marciano[] marcianos;
    ArrayList<Bala> balas = new ArrayList<>();
   

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana frame = new Ventana();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public class Marciano {
	    int[] x;
	    int[] y;
	    int dy;
	    int limiteSuperior;
	    int limiteInferior;
	    int retraso;

	    public Marciano(int[] x, int[] y, int dy, int limiteSuperior, int limiteInferior, int retraso) {
	        this.x = new int[x.length];
	        this.y = new int[y.length];
	        for (int i = 0; i < x.length; i++) {
	            this.x[i] = x[i];
	            this.y[i] = y[i];
	        }
	        this.dy = dy;
	        this.limiteSuperior = limiteSuperior;
	        this.limiteInferior = limiteInferior;
	        this.retraso = retraso;
	    }

	    public void mover() {
	        if (retraso > 0) {
	            retraso--;
	            return;
	        }
	        for (int i = 0; i < x.length; i++) {
	            y[i] += dy;
	        }
	    }

	    public Polygon getPoligono() {
	        return new Polygon(x, y, x.length);
	    }
	}
	
	public class Bala {
	    int x;
	    int y;
	    int dy;

	    public Bala(int x, int y, int dy) {
	        this.x = x;
	        this.y = y;
	        this.dy = dy;
	    }

	    public void mover() {
	        y -= dy;
	    }

	    public void dibujar(Graphics g) {
	        g.fillOval(x, y, 10, 25);
	    }
	    
	    public Polygon getPoligono() {
	        return new Polygon(new int[]{x, x+10, x+10, x}, new int[]{y, y, y+25, y+25}, 4);
	    }
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBounds(0, 0, 800, 600);
		contentPane.setBackground(Color.black);
		
		contentPane.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                trianguloX = e.getX() - 10; // Ajusta estos valores según la forma de tu triángulo
                trianguloY = e.getY() - 10;
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // No necesitamos implementar este método para tu caso
            }
        });

		marcianos = new Marciano[5]; // Crea 5 marcianos
        for (int i = 0; i < marcianos.length; i++) {
            int[] x = {120 + i * 100, 120 + i * 100, 160 + i * 100, 200 + i * 100, 200 + i * 100}; // Aumenta la separación horizontal
            int[] y = {160, 240, 200, 240, 160};
            marcianos[i] = new Marciano(x, y, 1, 100, 300, i * 50);
        }

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            	Iterator<Bala> it = balas.iterator();
                while (it.hasNext()) {
                    Bala bala = it.next();
                    bala.mover();
                    Polygon poligonoBala = bala.getPoligono();

                    for (int i = 0; i < marcianos.length; i++) {
                        Marciano marciano = marcianos[i];
                        if (marciano != null) {
                            marciano.mover();
                            // Verifica si algún marciano ha pasado la línea roja
                            if (marciano.y[0] >= 402) {
                                JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);  // Esto cerrará la aplicación
                            }

                            // Verifica si la bala ha interceptado a un marciano
                            if (poligonoBala.intersects(marciano.getPoligono().getBounds2D())) {
                                marcianos[i] = null; // Elimina el marciano
                                it.remove(); // Elimina la bala
                                break; // Sal del bucle de marcianos
                            }
                        }
                    }

                    if (bala.y < 0) {
                        it.remove(); // Elimina las balas que salen de la pantalla
                    }
                }

                repaint();
            }
        });
        timer.start();

        Timer timerBala = new Timer(500, new ActionListener() { // Dispara una bala cada 500 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                balas.add(new Bala(trianguloX + 25, trianguloY, 5)); // Ajusta estos valores según la forma de tu nave
            }
        });
        timerBala.start();
        
        Timer timerMarciano = new Timer(1000, new ActionListener() { // Genera un nuevo marciano cada 2000 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < marcianos.length; i++) {
                    if (marcianos[i] == null) {
                        marcianos[i] = generarMarciano();
                        break;
                    }
                }
            }
        });
        timerMarciano.start();
	}
	
	public Marciano generarMarciano() {
	    Random rand = new Random();
	    int x = rand.nextInt(getWidth() - 200); // Asegúrate de que el marciano se genere dentro de la ventana
	    int[] marcianoX = { x, x, x + 40, x + 80, x + 80 };
	    int[] marcianoY = { 0, 80, 40, 80, 0 };
	    return new Marciano(marcianoX, marcianoY, 1, 0, getHeight() - 100, 0);
	}

	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(Color.red);
		g.drawLine(0, 402, 800, 402);

		g.setColor(Color.white);
	//	g.fillOval(455, 380, 10, 25);

		int[] trianguloXs = {trianguloX, trianguloX + 50, trianguloX - 50};
        int[] trianguloYs = {trianguloY, trianguloY + 50, trianguloY + 50};
        g.fillPolygon(trianguloXs, trianguloYs, 3);
        
        g.setColor(Color.white);
        for (Bala bala : balas) {
            bala.dibujar(g);
        }

        g.setColor(Color.green);
        for (Marciano marciano : marcianos) {
            if (marciano != null) {  // Solo dibuja los marcianos que no son null
                g.fillPolygon(marciano.getPoligono());
            }
        }
	}
}
