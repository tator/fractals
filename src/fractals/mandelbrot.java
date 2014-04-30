/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fractals;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;

/**
 *
 * @author Baron Laptop
 */
public class mandelbrot extends JApplet {
    Color[] col;
    int v,n=100;
    double t,d=-10;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    @Override
    public void init() {
        col =new Color[]{Color.MAGENTA,Color.RED,Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
    
    }
    public void paint(Graphics g){
        v=Math.min(getSize().height, getSize().width);
        g.setColor(Color.gray);
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.black);
        g.fillRect(0, 0, v, v);
        t = 4.0/v;
        for(int a= 0;a<=v;a++){
            for(int b = 0; b<= v;b++){
                double x=0.0, y = 0.0,u=0.0;
                int q=0;
                while(q<=n&&(x*x+y*y)<4){
                    if(x==0.0 && y==0.0){
                    u = (t*a-2);
                    }else if(x!=0||y!=0){
                        u=Math.pow((x*x +y*y), (d/2.0))*Math.cos(d*Math.atan2(y, x))+(t*a-2);
                    }
                    if(x==0&&y==0){
                        y = (t*b-2);
                    }else if(x!=0||y!=0){
                        y=Math.pow((x*x+y*y), (d/2.0))*Math.sin(d*Math.atan2(y, x))+(t*b-2);
                    }
                    
                    x=u;
                    q++;
                }
                g.setColor(col[q%col.length]);
                if(x*x+y*y<4){
                    g.setColor(Color.BLACK);
                }
                g.drawLine(a, b, a, b);
            }
        }
        
    }

    // TODO overwrite start(), stop() and destroy() methods
}
