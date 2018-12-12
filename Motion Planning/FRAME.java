/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daproj;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mahshid
 */
public class FRAME extends JFrame {

    public PANEL pan;
    ArrayList<Shape> shapes;

    public FRAME() throws HeadlessException {
        shapes = new ArrayList();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.action();
        pan = new PANEL(shapes);
        add(pan);

    }

    public void action() {

        this.setEnabled(false);
        this.setVisible(false);
        Scanner sc = new Scanner(System.in);
        String s;
        int num;
        int numv;
        System.out.println("what is the main polygon? enter the number!");
        numv = sc.nextInt();
        Shape sh = new Shape(numv);
        sh.main = true;
        shapes.add(sh);
        System.out.println("enter the numbers of internal polygons");
        num = sc.nextInt();
        int pols[] = new int[num];
        System.out.println("what are the internal polygons? enter the numbers respectively!");
        System.out.println("then loacte the vertices on the new window respectively!");
        System.out.println("then locate the start and end point respectively!");
        for (int i = 0; i < num; i++) {
            int vert = sc.nextInt();
            Shape shi = new Shape(vert);
            shapes.add(shi);
        }
        this.setEnabled(true);
        this.setVisible(true);

    }
}
