/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractals;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Baron Laptop
 */
public class mandelbrot extends JPanel implements MouseListener, KeyListener {

    Color[] col = {Color.MAGENTA, Color.RED, Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
    int v, n = 100;
    double t, d = 2;
    double inc = 1;
    public static double xmin, xmax, ymin, ymax;

    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */

    public mandelbrot() {
        xmin = -2.0;
        xmax = 2.0;
        ymin = -2.0;
        ymax = 2.0;
        addMouseListener(this);
        addKeyListener(this);
        repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        v = Math.min(getSize().height, getSize().width);
        g.setColor(Color.gray);
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.black);
        g.fillRect(0, 0, v, v);
        t = (xmax - xmin) / (double) v;
        //System.out.println(t+"\n"+xmin +"\n"+xmax);
        for (int a = 0; a <= v; a++) {
            for (int b = 0; b <= v; b++) {
                double x = 0.0, y = 0.0, u = 0.0;
                int q = 0;
                while (q <= n && (x * x + y * y) < 4) {
                    if (x == 0.0 && y == 0.0) {
                        u = (t * a + xmin);
                    } else if (x != 0 || y != 0) {
                        u = Math.pow((x * x + y * y), (d / 2.0)) * Math.cos(d * Math.atan2(y, x)) + (t * a +xmin);
                    }
                    if (x == 0 && y == 0) {
                        y = (t * b + ymin);
                    } else if (x != 0 || y != 0) {
                        y = Math.pow((x * x + y * y), (d / 2.0)) * Math.sin(d * Math.atan2(y, x)) + (t * b +ymin);
                    }

                    x = u;
                    q++;
                }
                g.setColor(col[q % col.length]);
                if (x * x + y * y < 4) {
                    g.setColor(Color.BLACK);
                }
                g.drawLine(a, b, a, b);
            }
        }

    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 0x25) {
            inc = inc / 10.0;
        }
        if (e.getKeyCode() == 0x27) {
            inc = inc * 10;
        }
        if (e.getKeyCode() == 0x26) {
            d += inc;

        }
        if (e.getKeyCode() == 0x28) {
            d -= inc;
        }
        if (Math.abs(d - ((int) (d + .5))) <= 0.0000000005) {
            d = (double) ((int) (d + .5));
        }
        System.out.println(inc + " " + d);
        System.out.println(Math.abs(d - ((int) (d + .5))) + "\n");
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
