package fractals;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class treeFractal extends JPanel implements KeyListener {

    int v,vh=0,vw=0, o = 30, n =3, f1 = 4, f2 = 4;
    boolean p = false, s = true,c =true;
    Color[] col = new Color[]{Color.MAGENTA, Color.RED, Color.PINK, Color.YELLOW, Color.GREEN, Color.BLUE};
    
    //ArrayList<Point> arr = new ArrayList<Point>();
    public treeFractal() {
        addKeyListener(this);
        v = Math.min(getSize().height, getSize().width);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        p = true;
        //  arr = new ArrayList<Point>();
        v = Math.min(getSize().height, getSize().width);
        if(v ==getSize().height){
            vh =0;
            vw = (int)((getSize().width-v)/2.0);
        }else if(v == getSize().width){
            vw = 0;
            vw = (int)((getSize().height-v)/2.0);
        }else{
            vh =0;
            vw = 0;
        }
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.black);
        g.fillRect(0+vw, 0+vh, v, v);
        //System.out.println("v "+v);
        trunk(g);
        //g.setColor(Color.red);
        // for(int q = 0; q+1<arr.size();q++){
        //  g.drawLine(arr.get(q).x, arr.get(q).y, arr.get(q+1).x,arr.get(q+1).y);
        //}

        p = false;
    }

    public void trunk(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(("o: " + o + " n: " + n + (s ? " f1" : " f2")), 50, 50);
        g.drawString("f1 " + f1 + " f2 " + f2, 50, 60);
        g.drawLine(v / 2 + vw, (7 * v) / 8+vh, v / 2+vw, (3 * v) / 4+vh);
        //branch(g, 1, 90 - o-o-o, f1, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
        //branch(g, 1, 90 - o-o, f1, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
       branch(g, 1, 90 - o, f1, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
       //branch(g, 1, 90 , f1, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
        branch(g, 1, 90 + o, f2, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
        //branch(g, 1, 90 + o+o, f1, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
        
        //branch(g, 1, 90 + o+o+o, f1, v / 2.0, (3.0 * v) / 4.0, v / 8.0);
    }

    public void branch(Graphics g, int nn, int oo, int f, double x, double y, double l) {
        if (nn == n) {
            //  arr.add(new Point((int)x,(int)y));
            return;
        }
        
        double ll = l - (l / (double) f);
        double xx = ll * Math.cos(Math.PI * (oo / 180.0)) + x;
        double yy = y - (ll * Math.sin(Math.PI * (oo / 180.0)));
        //System.out.println("ll "+ll+"\nxx "+xx+"\nyy "+yy+"\nsin"+Math.sin(Math.PI*(oo/180.0))+"\n");
        if(c){
        g.setColor(col[nn%col.length]);
        }
        g.drawLine((int) x+vw, (int) y+vh, (int) xx+vw, (int) yy+vh);
        nn++;
        //branch(g, nn, oo -o-o- o, f2, xx, yy, ll);
        //branch(g, nn, oo -o- o, f2, xx, yy, ll);
        branch(g, nn, oo - o, f1, xx, yy, ll);
        //branch(g, nn, oo , f1, xx, yy, ll);
        branch(g, nn, oo + o, f2, xx, yy, ll);
        //branch(g, nn, oo + o+o, f2, xx, yy, ll);
        //branch(g, nn, oo + o+o+o, f2, xx, yy, ll);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        //if (!p) {
        if (k.getKeyCode() == 38) {
            o++;
            if (o > 180) {
                o = 180;
            }
        }
        if (k.getKeyCode() == 40) {
            o--;
            if (o < 0) {
                o = 0;
            }
        }
        if (k.getKeyCode() == 45) {
            n--;
            if (n == 1) {
                n = 2;
            }
        }
        if (k.getKeyCode() == 61) {
            n++;
        }
        if (k.getKeyCode() == 16) {
           s = !s;
        }
        if (k.getKeyCode() == 37) {
            if (s) {
                f1--;
                //^f2--;
                if (f1 == 1) {
                    f1 = 2;
                  //  f2=2;
                }

            } else {
                f2--;
                if (f2 == 1) {
                    f2 = 2;
                }
            }
        }
        if (k.getKeyCode() == 39) {
            if (s) {
                f1++;
                //f2++;
            } else {
                f2++;
            }
        }
        if(k.getKeyCode()==0x43){
            c = !c;
        }
        //System.out.println(k.getKeyChar() + "\n" + k.getKeyCode() + "\n");
        repaint();
        //}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
