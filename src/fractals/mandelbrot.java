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
    int v, n = 50, count = 0;
    double t, d = 2;
    double inc = 1;
    public static double xmin, xmax, ymin, ymax;
    double xrhold = 4.0, yrhold = 4.0;
    double pointX, pointY;
    double zoom_scale = 4.0;
    boolean show = false;

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
        t = t();
        int lastper = 0;
        //System.out.println(t+"\n"+xmin +"\n"+xmax);
        for (int a = 0; a <= v; a++) {
            for (int b = 0; b <= v; b++) {
                if ((int) (n * Math.pow(1. + (1.0 / (zoom_scale * 2.0)), count)) >= 150
                        && lastper != (int) (((a * v + b) / (double) (v * v)) * 100)) {
                    lastper = (int) (((a * v + b) / (double) (v * v)) * 100);
                    System.out.printf("%d\n", (int) (((a * v + b) / (double) (v * v)) * 100));

                } else if ((int) (n * Math.pow(1. + (1.0 / (zoom_scale * 2.0)), count)) <= 150
                        && lastper != 10 * (int) (((a * v + b) / (double) (v * v)) * 10)) {
                    lastper = 10 * (int) (((a * v + b) / (double) (v * v)) * 10);
                    System.out.printf("%d\n", 10 * (int) (((a * v + b) / (double) (v * v)) * 10));
                }
                double x = 0.0, y = 0.0, u = 0.0;
                int q = 0;
                while (q <= (int) (n * Math.pow(1. + (1.0 / (zoom_scale * 2.0)), count)) && (x * x + y * y) < 4) {
                    if (x == 0.0 && y == 0.0) {
                        u = (t * a + xmin);
                    } else if (x != 0 || y != 0) {
                        u = Math.pow((x * x + y * y), (d / 2.0)) * Math.cos(d * Math.atan2(y, x)) + (t * a + xmin);
                    }
                    if (x == 0 && y == 0) {
                        y = (t * b + ymin);
                    } else if (x != 0 || y != 0) {
                        y = Math.pow((x * x + y * y), (d / 2.0)) * Math.sin(d * Math.atan2(y, x)) + (t * b + ymin);
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
        if (show) {
            showing();
        }
    }

    public void showing() {
        xrhold = 4.0;
        yrhold = 4.0;
        xrhold = xRange() * zoom_scale;
        yrhold = yRange() * zoom_scale;
        count--;
        if (xrhold > 4.0 && yrhold > 4.0) {
            xrhold = 4.0;
            yrhold = 4.0;
            show = false;
        }
        //System.out.println(xrhold + " " + yrhold);
        xmin = pointX - (xrhold / 2.0);
        xmax = pointX + (xrhold / 2.0);
        if (xmin < -2.0) {
            xmin = -2.0;
            xmax = -2.0 + xrhold;
        }
        if (xmax > 2.0) {
            xmax = 2.0;
            xmin = 2.0 - xrhold;
        }
        if(yrhold!=xrhold){
            yrhold = Math.max(yrhold,xrhold);
            xrhold=yrhold;
        }
        ymin = pointY - (yrhold / 2.0);
        ymax = pointY + (yrhold / 2.0);
        if (ymin < -2.0) {
            ymin = -2.0;
            ymax = -2.0 + xrhold;
        }
        if (ymax > 2.0) {
            ymax = 2.0;
            ymin = 2.0 - xrhold;
        }
        System.out.println("inc: " + inc + " d: " + d);
        System.out.println("count: " + count + " n: " + (int) (n * Math.pow(1. + (1.0 / (zoom_scale * 2.0)), count)));
        System.out.println("showing "+show);
        System.out.println("x range: "+xrhold + " y ranger: " + yrhold);
        System.out.println("xmin: " + xmin + " xmax: " + xmax);
        System.out.println("ymin: " + ymin + " ymax: " + ymax);
        System.out.println();
        if (show) {
            repaint();
        }
    }

    public double t() {
        return xRange() / (double) v;
    }

    public double xRange() {
        return (xmax - xmin);
    }

    public double yRange() {
        return (ymax - ymin);
    }

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pointX = t() * e.getPoint().x + xmin;
        pointY = t() * e.getPoint().y + ymin;
        System.out.println(pointX + " " + pointY);
        //System.out.println(e.getButton());
        xrhold = 4.0;
        yrhold = 4.0;
        if (e.getButton() == 1) {
            xrhold = xRange() / zoom_scale;
            yrhold = yRange() / zoom_scale;
            count++;
        } else if (e.getButton() == 2) {
            xmin = -2.0;
            xmax = 2.0;
            ymin = -2.0;
            ymax = 2.0;
            count = 0;
            repaint();
            return;
        } else if (e.getButton() == 3) {
            xrhold = xRange() * zoom_scale;
            yrhold = yRange() * zoom_scale;
            count--;
        }
        if(xrhold != yrhold){
            xrhold = Math.max(xrhold, yrhold);
            yrhold = xrhold;
        }
        if (xrhold > 4.0 && yrhold > 4.0) {
            xrhold = 4.0;
            yrhold = 4.0;
        }
        
        xmin = pointX - (xrhold / 2.0);
        xmax = pointX + (xrhold / 2.0);
        if (xmin < -2.0) {
            xmin = -2.0;
            xmax = -2.0 + xrhold;
        }
        if (xmax > 2.0) {
            xmax = 2.0;
            xmin = 2.0 - xrhold;
        }
        ymin = pointY - (yrhold / 2.0);
        ymax = pointY + (yrhold / 2.0);
        if (ymin < -2.0) {
            ymin = -2.0;
            ymax = -2.0 + xrhold;
        }
        if (ymax > 2.0) {
            ymax = 2.0;
            ymin = 2.0 - xrhold;
        }
        System.out.println("inc: " + inc + " d: " + d);
        System.out.println("count: " + count + " n: " + (int) (n * Math.pow(1. + (1.0 / (zoom_scale * 2.0)), count)));
        System.out.println("showing "+show);
        System.out.println("x range: "+xrhold + " y ranger: " + yrhold);
        System.out.println("xmin: " + xmin + " xmax: " + xmax);
        System.out.println("ymin: " + ymin + " ymax: " + ymax);
        System.out.println();
        repaint();
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
        if (e.getKeyCode() == 0x53) {
            show = !show;
        }
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
        if (Math.abs(d - ((int) (d + ((d > 0) ? .5 : -5)))) <= 0.0000000005) {
            d = (double) ((int) (d + .5));
        }
        System.out.println("inc: " + inc + " d: " + d);
        System.out.println("count: " + count + " n: " + (int) (n * Math.pow(1. + (1.0 / (zoom_scale * 2.0)), count)));
        System.out.println("showing "+show);
        System.out.println("x range: "+xrhold + " y ranger: " + yrhold);
        System.out.println("xmin: " + xmin + " xmax: " + xmax);
        System.out.println("ymin: " + ymin + " ymax: " + ymax);
        System.out.println();
        if (e.getKeyCode() == 0x20) {
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
