package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import Interfaces.Entidad;

public class Nave implements Entidad {
    int x;
    int y;

    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover(int x, int y) {
        this.x = x - 10; // Ajusta estos valores según la forma de tu triángulo
        this.y = y - 10;
    }

    @Override
    public void mover() {
        // No necesitamos implementar este método para tu caso
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.white);
        int[] trianguloXs = {x, x + 50, x - 50};
        int[] trianguloYs = {y, y + 50, y + 50};
        g.fillPolygon(trianguloXs, trianguloYs, 3);
    }

    @Override
    public Polygon getPoligono() {
        return new Polygon(new int[]{x, x + 50, x - 50}, new int[]{y, y + 50, y + 50}, 3);
    }
}
