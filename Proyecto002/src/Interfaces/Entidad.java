package Interfaces;

import java.awt.Graphics;
import java.awt.Polygon;

public interface Entidad {
    public void mover();
    public void dibujar(Graphics g);
    Polygon getPoligono();
}
