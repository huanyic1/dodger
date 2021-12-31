/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 Shenendehowa High School
  
 File: $(name).java
 Date: $(date)
 Purpose:
 Author: Yicheng Huang
 Secret Sauce Code: 4

 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
package maze;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Dodger implements ActionListener, KeyListener {

    public JFrame jframe;

    public static Dodger avatar;

    public RenderPanel renderPanel;

    public Timer timer = new Timer(20, this);

    public int height = 400, width = 240;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public int ticks = 0, direction = DOWN, score, taillength;
    public static int scale = 40;
    public boolean over = false, paused;
    public Point head, cherry;
    public Random random;

    public int numMovingBombs;
    public ArrayList<Point> MovingBomb = new ArrayList<Point>();
    public ArrayList<Point> Cherries = new ArrayList<Point>();

    public Dodger() { //constructor for jframe

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Dodger");
        jframe.setVisible(true);
        jframe.setSize(250, 430);
        jframe.setLocationRelativeTo(null); //centers jframe
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGame();
    }

    public void startGame() {
        ticks = 0;

        Cherries.clear();
        MovingBomb.clear();
        over = false;
        paused = false;
        random = new Random();
        score = 0;

        ///MOVING BOMBS
        numMovingBombs = 1;
//       for(int r= 60; r< height; r+=scale*8){ 
//         for(int c= 40; c< width; c+=4*scale){
//                 MovingBomb.add(new Point(c, r));
//            
//        } 
//       }
        for (int i = 0; i < numMovingBombs; i++) {
            //  MovingBomb.add(new Point(40, 360)); 
            // MovingBomb.add(new Point(scale * (random.nextInt(width / scale - 10) + 5), scale * (random.nextInt(height / scale - 10) + 5)));
        }

        //HEAD
        head = new Point(120, 360);
       // head = new Point(scale * (random.nextInt(width / scale - 10) + 5), scale * (random.nextInt(height / scale - 10) + 5));

        ///CHERRY
        cherry = new Point(760, 560);
      //  cherry = new Point(scale * random.nextInt(width / scale), scale * random.nextInt(height / scale));

        //avatarParts.add(new Point(head.x, head.y));
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        renderPanel.repaint();
        ticks++;
//        if (ticks % 1 == 0 && head != null && !over && !paused) {
//
//            if (cherry != null) {
        if (ticks % 75 == 0) {
            int c1 = (int) (6 * Math.random());
            Cherries.add(new Point(c1 * scale, -40));
            if (!over) {
                score += 10;
            }

        }
        if (ticks % 15 == 0) {
            int c = (int) (6 * Math.random());
            MovingBomb.add(new Point(c * scale, -40));

        }
        if (ticks % 15 == 0) {

            for (int i = 0; i < MovingBomb.size(); i++) {
                MovingBomb.get(i).setLocation(MovingBomb.get(i).x, MovingBomb.get(i).y + scale);
            }
            for (int i = 0; i < Cherries.size(); i++) {
                Cherries.get(i).setLocation(Cherries.get(i).x, Cherries.get(i).y + scale);
            }

        }

        if (head.x == cherry.x && head.y == cherry.y) {
            score += 20;
            cherry.setLocation(scale * random.nextInt(width / scale), scale * random.nextInt(height / scale));

        }

        if (!noMovingBomb(head.x, head.y)) {
            over = true;

        }
        if (hitCherry(head.x, head.y)) {
            score += 10;

        }

//            }
//        }
    }

    public boolean noMovingBomb(int x, int y) { //checks if head collides with moving bomb
        for (Point point : MovingBomb) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    public boolean hitCherry(int x, int y) { //checks if head collides with moving bomb
        for (int i = 0; i < Cherries.size(); i++) {
            if (Cherries.get(i).equals(new Point(x, y))) {
                Cherries.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int i = ke.getKeyCode();
        if (!over) {
//        if (i == KeyEvent.VK_UP&&head.y!=0) {//moving up
//
//            head.y -= scale;
//
//        }
//        if (i == KeyEvent.VK_DOWN&&head.y!=height-scale) { //moving down
//
//            head.y += scale;
//
//        }
            if (i == KeyEvent.VK_LEFT && head.x != 0) {//moving left

                head.x -= scale;

            }
            if (i == KeyEvent.VK_RIGHT && head.x != width - scale) { //moving right
                head.x += scale;

            }
        }
        if (i == KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            } else {
                paused = !paused;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        avatar = new Dodger();
    }

}
