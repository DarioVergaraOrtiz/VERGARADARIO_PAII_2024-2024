package ec.edu.uce.Proyecto_004;

import InterfazGrafica.VentanaGrafica;

import javax.swing.*;

/**
 *  @author: Dario Vergara
 *  Tittle: Hibernate
 */

public class App 
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame("VentanaTest");
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        VentanaGrafica gui = new VentanaGrafica();
        frame.add(gui.getPanel());

        frame.setVisible(true);
    }
}
