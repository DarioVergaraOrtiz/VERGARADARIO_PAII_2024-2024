package Principal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Interfaces.Entidad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Ventana extends JFrame {

    private JPanel contentPane;
    private ArrayList<Entidad> entidades = new ArrayList<>();
    

    
    public Ventana() {
    	entidades.add(new Nave(400, 450));
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
                for (Entidad entidad : entidades) {
                    if (entidad instanceof Nave) {
                        ((Nave) entidad).mover(e.getX(), e.getY());
                    }
                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // No necesitamos implementar este método para tu caso
            }
        });

        for (int i = 0; i < 5; i++) {
            entidades.add(new Marciano(120 + i * 100, 160, 1, 100, 300, i * 50));
        }

        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	ArrayList<Entidad> entidadesAEliminar = new ArrayList<>();

            	for (Entidad entidad : entidades) {
            	    if (entidad instanceof Marciano) {
            	        Marciano marciano = (Marciano) entidad;
            	        if (marciano.y[0] >= 402) {
            	            JOptionPane.showMessageDialog(null, "Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            	            System.exit(0);  // Esto cerrará la aplicación
            	        }

            	        for (Entidad otraEntidad : entidades) {
            	            if (otraEntidad instanceof Bala) {
            	                Bala bala = (Bala) otraEntidad;
            	                if (marciano.getPoligono().intersects(bala.getPoligono().getBounds2D())) {
            	                    entidadesAEliminar.add(marciano);
            	                    entidadesAEliminar.add(bala);
            	                    break; // Sal del bucle de marcianos
            	                }
            	            }
            	        }
            	    } else if (entidad instanceof Bala) {
            	        Bala bala = (Bala) entidad;
            	        if (bala.y < 0) {
            	            entidadesAEliminar.add(bala);
            	        }
            	    }
            	}

            	entidades.removeAll(entidadesAEliminar);

                repaint();
            }
        });
        timer.start();

        Timer timerBala = new Timer(500, new ActionListener() { // Dispara una bala cada 500 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Entidad entidad : entidades) {
                    if (entidad instanceof Nave) {
                        Nave nave = (Nave) entidad;
                        entidades.add(new Bala(nave.x + 25, nave.y, 5)); // Ajusta estos valores según la forma de tu nave
                    }
                }
            }
        });
        timerBala.start();

        Timer timerMarciano = new Timer(1000, new ActionListener() { // Genera un nuevo marciano cada 2000 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                entidades.add(generarMarciano());
            }
        });
        timerMarciano.start();
    }

    public Marciano generarMarciano() {
        Random rand = new Random();
        int x = rand.nextInt(getWidth() - 200); 
        return new Marciano(x, 0, 1, 0, getHeight() - 100, 0);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.red);
        g.drawLine(0, 402, 800, 402);

        for (Entidad entidad : entidades) {
            entidad.dibujar(g);
        }
    }
}



