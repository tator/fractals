/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fractals;


import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.3
 */
public class mandel extends JApplet  {
    JFrame frame;
    mandelbrot mand;
    @Override
    public void init() {
        //cb = new cb(); 
        //getContentPane().add(new cb()); 


        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(840, 840);
        frame.setLocation(100, 100);
        frame.setTitle("Mandelbrot set");

        Container contentpane = frame.getContentPane();
        contentpane.add(mand = new mandelbrot());
        mand.requestFocus();
       
    }

}