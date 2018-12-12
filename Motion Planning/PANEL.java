/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daproj;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import javax.swing.JPanel;

/**
 *
 * @author Mahshid
 */
public class PANEL extends JPanel {

    HandlerMouse handler = new HandlerMouse();
    PriorityQueue<Vertice> far;
    ArrayList<Shape> shapes;
    ArrayList<Integer> xs;
    ArrayList<Integer> ys;
    ArrayList<Polygon> poly;
    ArrayList<Segmento> segs;
    Vertice allverts[];
    double matrix[][];
    int x, y, dia;
    int turn = 0;
    int total = 0;
    int startpx, endpx, startpy, endpy;
    Graphics g;

    public PANEL(ArrayList<Shape> shapes) {
        xs = new ArrayList();
        ys = new ArrayList();
        poly = new ArrayList();
        segs = new ArrayList();
        this.shapes = shapes;
        addMouseListener(handler);
        dia = 10;
        for (Shape s : shapes) {
            total += s.verts;
        }
        System.out.println("total" + total);
        allverts = new Vertice[total + 2];
        for (int vv = 0; vv < total + 2; vv++) {
            allverts[vv] = new Vertice();
        }
        matrix = new double[total + 2][total + 2];
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (turn <= total) {
            for (int d = 0; d < xs.size(); d++) {
                x = xs.get(d) - (int) (Math.floor(dia / 2));
                y = ys.get(d) - (int) (Math.floor(dia / 2));
                g.setColor(new Color(214, 16, 85));
                g.fillOval(x, y, dia, dia);
                String s = Integer.toString(d + 1);
                g.drawString(s, x, y);
                if (turn < shapes.get(0).verts) {
                    g.drawString("main polygon...", 20, 20);
                } else {
                    if (turn != total) {
                        g.drawString("internal polygon...", 20, 20);
                    }
                }
            }

            if (turn == total) {
                int start = 0;
                Segmento seg;
                g.drawString("Start point...", 20, 20);
                for (Shape sh : shapes) {

                    for (int count = 0; count < sh.verts; count++) {
                        sh.xofverts[count] = xs.get(start);
                        sh.yofverts[count] = ys.get(start);
                        sh.xof.add(xs.get(start));
                        sh.yof.add(ys.get(start));
                        start++;
                        if (count != 0) {
                            seg = new Segmento(sh.xofverts[count - 1], sh.yofverts[count - 1],
                                    sh.xofverts[count], sh.yofverts[count]);
                            segs.add(seg);
                        }
                    }
                    seg = new Segmento(sh.xofverts[sh.verts - 1], sh.yofverts[sh.verts - 1],
                            sh.xofverts[0], sh.yofverts[0]);
                    segs.add(seg);
                    Polygon p = new Polygon(sh.xofverts, sh.yofverts, sh.verts);
                    //   System.out.println(p.contains(sh.xofverts[1], sh.yofverts[1])+ "contain");
                    poly.add(p);
                    if (poly.indexOf(p) == 0) {
                        g.setColor(new Color(113, 60, 143));
                        g.drawPolygon(p);
                    } else {
                        //   g.drawPolygon(ps);
                        g.setColor(new Color(193, 168, 196));
                        g.fillPolygon(p);
                    }
                }
                for (int d = 0; d < xs.size(); d++) {
                    x = xs.get(d) - (int) (Math.floor(dia / 2));
                    y = ys.get(d) - (int) (Math.floor(dia / 2));
                    g.setColor(new Color(214, 16, 85));
                    g.fillOval(x, y, dia, dia);
                    String s = Integer.toString(d + 1);
                    g.drawString(s, x, y);
                }
            }
        }
        if (turn == total + 1) {
            for (Polygon ps : poly) {
                g.setColor(new Color(113, 60, 143));
                if (poly.indexOf(ps) == 0) {
                    g.setColor(new Color(113, 60, 143));
                    g.drawPolygon(ps);
                } else {
                    //   g.drawPolygon(ps);
                    g.setColor(new Color(193, 168, 196));
                    g.fillPolygon(ps);
                }
            }
            for (int d = 0; d < xs.size(); d++) {
                x = xs.get(d) - (int) (Math.floor(dia / 2));
                y = ys.get(d) - (int) (Math.floor(dia / 2));
                g.setColor(new Color(214, 16, 85));
                g.fillOval(x, y, dia, dia);
                String s = Integer.toString(d + 1);
                g.drawString(s, x, y);
            }
            g.drawString("End point...", 20, 20);
            g.setColor(new Color(237, 132, 54));
            x = startpx - (int) (Math.floor(15 / 2));
            y = startpy - (int) (Math.floor(15 / 2));
            g.fillOval(x, y, 15, 15);
            g.drawString("start", x, y);

        }
        if (turn == total + 2) {
            g.setColor(new Color(214, 16, 85));
            g.drawString("You can see the shortest path in green...", 20, 20);

            for (Polygon ps : poly) {
                g.setColor(new Color(113, 60, 143));
                if (poly.indexOf(ps) == 0) {
                    g.drawPolygon(ps);
                } else {
                    //   g.drawPolygon(ps);
                    g.setColor(new Color(193, 168, 196));
                    g.fillPolygon(ps);
                }
            }
            for (int d = 0; d < xs.size(); d++) {
                x = xs.get(d) - (int) (Math.floor(dia / 2));
                y = ys.get(d) - (int) (Math.floor(dia / 2));
                g.setColor(new Color(214, 16, 85));
                g.fillOval(x, y, dia, dia);
                String s = Integer.toString(d + 1);
                g.drawString(s, x, y);
                // String s1 = Integer.toString(y);
                // g.drawString(s1, x, y - 10);
            }

            g.setColor(new Color(237, 132, 54));
            x = startpx - (int) (Math.floor(15 / 2));
            y = startpy - (int) (Math.floor(15 / 2));
            g.fillOval(x, y, 15, 15);
            //  String s = Integer.toString(x);
            g.drawString("start", x, y);
            //   String s1 = Integer.toString(y);
            //  g.drawString(s1, x, y - 10);

            g.setColor(new Color(61, 157, 194));
            x = endpx - (int) (Math.floor(15 / 2));
            y = endpy - (int) (Math.floor(15 / 2));
            g.fillOval(x, y, 15, 15);
            //  s = Integer.toString(x);
            g.drawString("end", x, y);
            //  s1 = Integer.toString(y);
            //   g.drawString(s1, x, y - 10);

            matrixgen();
            for (int r = 0; r < total + 2; r++) {
                for (int c = r; c < total + 2; c++) {
                    if (matrix[r][c] < 1000000 && matrix[r][c] != 0) {
                        g.setColor(new Color(113, 60, 143));
                        g.drawLine(allverts[r].x, allverts[r].y, allverts[c].x, allverts[c].y);
                    }
                }
            }
            g.setColor(new Color(237, 132, 54));
            x = startpx - (int) (Math.floor(15 / 2));
            y = startpy - (int) (Math.floor(15 / 2));
            g.fillOval(x, y, 15, 15);
            //  String s = Integer.toString(x);
            g.drawString("start", x, y);
            //   String s1 = Integer.toString(y);
            //  g.drawString(s1, x, y - 10);

            g.setColor(new Color(61, 157, 194));
            x = endpx - (int) (Math.floor(15 / 2));
            y = endpy - (int) (Math.floor(15 / 2));
            g.fillOval(x, y, 15, 15);
            //  s = Integer.toString(x);
            g.drawString("end", x, y);
            //  s1 = Integer.toString(y);
            //   g.drawString(s1, x, y - 10);

            dijstra();
            ArrayList<Vertice> way = way();
            Vertice s = allverts[total + 1];
            g.setColor(new Color(56, 250, 105));
            for (Vertice vava : way) {
                g.drawLine(s.x, s.y, vava.x, vava.y);
                s = vava;
            }
            g.drawLine(allverts[0].x, allverts[0].y, s.x, s.y);
        }
    }

    private class HandlerMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
                turn++;
                if (turn <= total) {
                    PANEL.this.xs.add(e.getX());
                    PANEL.this.ys.add(e.getY());
                }
                if (turn == total + 1) {
                    startpx = e.getX();
                    startpy = e.getY();
                }
                if (turn == total + 2) {
                    endpx = e.getX();
                    endpy = e.getY();
                }
                if (turn <= total + 2) {
                    PANEL.this.repaint();
                }
            }
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

    }

    double distance(int x1, int y1, int x2, int y2) {
        int difx = (x1 - x2);
        int dify = (y1 - y2);
        double powered = Math.pow(difx, 2) + Math.pow(dify, 2);
        double distance = Math.sqrt(powered);
        return distance;

    }

    Shape samepolygon(int x1, int y1, int x2, int y2) {

        for (Shape sha : shapes) {
            if (sha.xof.contains(x1) && sha.yof.contains(y1) && sha.xof.indexOf(x1) == sha.yof.indexOf(y1)) {
                if (sha.xof.contains(x2) && sha.yof.contains(y2) && sha.xof.indexOf(x2) == sha.yof.indexOf(y2)) {
                    return sha;
                }
            }
        }

        return null;
    }

    boolean insidepol(int x1, int y1, int x2, int y2, Polygon p) {
        double midx = (x1 + x2) / 2;
        double midy = (y1 + y2) / 2;
        if (p.contains(midx, midy) == true) {
            return true;
        }
        return false;
    }

    boolean problem(int x1, int y1, int x2, int y2) {
        boolean res = false;
        Segmento seg1 = new Segmento(x1, y1, x2, y2);
        if (samepolygon(x1, y1, x2, y2) != null) {
            Shape ss = samepolygon(x1, y1, x2, y2);
            if (Math.abs(ss.xof.indexOf(x1) - ss.xof.indexOf(x2)) == 1
                    || Math.abs(ss.xof.indexOf(x1) - ss.xof.indexOf(x2)) == ss.xof.size() - 1) {
                //   System.out.println("no! same and after "+ x1+ " " + y1+ " " + x2 + " "+ y2);
                return false;
            } else {
                for (Segmento sese : segs) {
                    res = sese.ishit(seg1);
                    if (res == true) {
                        // sese.prob.add(seg1);
                        return res;
                    }
                }
                Polygon p = poly.get(shapes.indexOf(ss));
                //  System.out.println("same and inside "+insidepol(x1, y1, x2, y2, p)+" " + x1+ " " + y1+ " " + x2 + " "+ y2);
                if (ss.main != true) {
                    return (insidepol(x1, y1, x2, y2, p));
                } else {
                    return true;
                }
            }
        } else {
            for (Segmento sese : segs) {
                res = sese.ishit(seg1);
                if (res == true) {
                    //   sese.prob.add(seg1);
                    return res;
                }
            }
            return res;
        }
    }

    void matrixgen() {
        double maxint = 1000000;
        allverts[0].x = startpx;
        allverts[0].y = startpy;
        allverts[total + 1].x = endpx;
        allverts[total + 1].y = endpy;
        for (int bb = 0; bb < total; bb++) {
            allverts[bb + 1].x = xs.get(bb);
            allverts[bb + 1].y = ys.get(bb);
        }
        for (int row = 0; row < total + 2; row++) {
            for (int col = row; col < total + 2; col++) {
                if (row == col) {
                    matrix[row][row] = 0;
                } else {
                    matrix[row][col] = distance(allverts[row].x, allverts[row].y, allverts[col].x, allverts[col].y);
                    matrix[col][row] = matrix[row][col];
                }
            }
        }
        for (int row = 0; row < total + 2; row++) {
            for (int col = row; col < total + 2; col++) {
                if (row != col) {
                    if (problem(allverts[row].x, allverts[row].y, allverts[col].x, allverts[col].y) == true) {
                        matrix[row][col] = maxint;
                        matrix[col][row] = matrix[row][col];
                    }
                }
                System.out.print(matrix[row][col] + "     ");
            }
            System.out.println();
        }
        for (Segmento segg : segs) {
            // System.out.println( segg.x1 + " :x1 " + segg.y1 + " :y1 " + segg.x2 + " :x2 " + segg.y2+ " :y2");
            //segg.printnoprob();
        }
    }

    void dijstra() {

        far = new PriorityQueue(total + 1, new Comparator<Vertice>() {
            @Override
            public int compare(Vertice v1, Vertice v2) {
                if (v1.startdistance > v2.startdistance) {
                    return 1;
                } else if (v1.startdistance < v2.startdistance) {
                    return -1;
                } else {
                    return 0;
                }

            }
        });
        allverts[0].startdistance = 0;
        allverts[0].index = 0;
        for (int jj = 1; jj < total + 2; jj++) {
            allverts[jj].index = jj;
            allverts[jj].startdistance = matrix[0][jj];
            far.add(allverts[jj]);
        }
        System.out.println(allverts[total + 1].startdistance + " before");
        while (far.size() != 0) {
            Vertice v = far.poll();
            for (int jojo = 1; jojo < total + 2; jojo++) {
                Vertice checking = allverts[jojo];
                if (v.startdistance + matrix[v.index][checking.index] < checking.startdistance) {
                    checking.middleman.add(v);
                    checking.startdistance = v.startdistance + matrix[v.index][checking.index];
                }
                if (jojo == total + 1) {
                    System.out.println(checking.startdistance + "  "
                            + v.startdistance + matrix[v.index][checking.index]);
                }
            }
        }
        //  System.out.println(allverts[total + 1].startdistance + " after");
        System.out.println(allverts[total + 1].middleman.size());
    }

    ArrayList<Vertice> way() {
        ArrayList<Vertice> route = new ArrayList();
        Vertice v1 = allverts[total + 1];
        while (!v1.middleman.isEmpty()) {
            Vertice v2 = v1.middleman.get(v1.middleman.size() - 1);
            route.add(v2);
            v1 = v2;
            // v2 = v2.middleman.get(v2.middleman.size()-1);
            System.out.println(v2.index + "way");
        }
        return route;
    }

}
