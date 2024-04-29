package Principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import Interfaces.Entidad;

public class Marciano implements Entidad {
    int[] x;
    int[] y;
    int dy;
    int limiteSuperior;
    int limiteInferior;
    int retraso;

    public Marciano(int x, int y, int dy, int limiteSuperior, int limiteInferior, int retraso) {
        this.x = new int[]{x, x, x + 40, x + 80, x + 80};
        this.y = new int[]{y, y + 80, y + 40, y + 80, y};
        this.dy = dy;
        this.limiteSuperior = limiteSuperior;
        this.limiteInferior = limiteInferior;
        this.retraso = retraso;
    }

    @Override
    public void mover() {
        if (retraso > 0) {
            retraso--;
            return;
        }
        for (int i = 0; i < x.length; i++) {
            y[i] += dy;
        }
    }

    @Override
    public Polygon getPoligono() {
        return new Polygon(x, y, x.length);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.green);
        g.fillPolygon(getPoligono());
    }
}
