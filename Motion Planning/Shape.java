/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daproj;

import java.util.ArrayList;

/**
 *
 * @author Mahshid
 */
public class Shape {

    boolean main;
    int verts;
    int xofverts[];
    int yofverts[];
    ArrayList<Integer> xof;
    ArrayList<Integer> yof;

    public Shape(int verts) {
        this.verts = verts;
        main = false;
        xofverts = new int[verts];
        yofverts = new int[verts];
        xof = new ArrayList();
        yof = new ArrayList();

    }

}
