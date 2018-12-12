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
public class Segmento {

    int x1, y1, x2, y2;
    float m;
    ArrayList<Segmento> prob;

    public Segmento(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        prob = new ArrayList();

        if (x2 != x1) {
            m = (float)(y2 - y1) / (x2 - x1);
        } else {
            m = 1000000;
        }

    }

    boolean ishit(Segmento seg) {
        double xfind;
        if (seg.m == this.m) {
            return false;
        } else if (seg.x1 == this.x1 && seg.y1 == this.y1) {
            return false;
        } else if (seg.x1 == this.x2 && seg.y1 == this.y2) {
            return false;
        } else if (seg.x2 == this.x2 && seg.y2 == this.y2) {
            return false;
        } else if (seg.x2 == this.x1 && seg.y2 == this.y1) {
            return false;
        } else {
           
            xfind = ((this.m * this.x1) - (seg.m * seg.x1) - this.y1 + seg.y1) / (this.m - seg.m);
          //  System.out.println(seg.x1 +  " "+ seg.x2 + " hiiiitttpp " + xfind + "    " + this.x1 + "  " +this.x2);
            if (xfind > Math.min(this.x1, this.x2) && xfind > Math.min(seg.x1, seg.x2) && 
                    xfind < Math.max(this.x1,this.x2) && xfind < Math.max(seg.x1 , seg.x2)) {
                
                return true;
            } else {
                return false;
            }
        }

    }

    void printnoprob() {

        for (int i = 0; i < prob.size(); i++) {

            System.out.println(this.x1 + " " + this.x2 + " prob:" + prob.get(i).x1 + " " + prob.get(i).x2);

        }
    }

}
