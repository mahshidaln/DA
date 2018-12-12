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
public class Vertice {
    
    int x, y, index;
    ArrayList<Vertice> middleman;
    double startdistance;
    boolean checked;
    

    public Vertice() {
        middleman = new ArrayList();
        x = y = -10;
        index = -1;
        startdistance = 1000000;
        checked = false;
    }
    
   
    
}
