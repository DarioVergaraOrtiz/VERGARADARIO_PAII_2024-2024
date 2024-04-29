package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import Interfaces.Entidad;

public class Bala implements Entidad {
    int x;
    int y;
    int dy;

    public Bala(int x, int y, int dy) {
        this.x = x;
        this.y = y;
        this.dy = dy;
    }

    @Override
    public void mover() {
        y -= dy;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, 10, 25);
    }

    @Override
    public Polygon getPoligono() {
        return new Polygon(new int[]{x, x + 10, x + 10, x}, new int[]{y, y, y + 25, y + 25}, 4);
    }
}
