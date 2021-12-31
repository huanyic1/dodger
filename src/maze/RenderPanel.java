/*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
 Shenendehowa High School
  
 File: $(name).java
 Date: $(date)
 Purpose:
 Author: Yicheng Huang
 Secret Sauce Code: 4

 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
package maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JPanel;

public class RenderPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Dodger avatar = Dodger.avatar;

        g.setColor(Color.WHITE); //board/grid
        g.fillRect(0, 0, avatar.width, avatar.height);
        g.setColor(Color.BLACK);
        for (int i = 0; i < avatar.width / Dodger.scale; i++) { ///drawing grid lines
            g.drawLine(i * Dodger.scale, 0, i * Dodger.scale, avatar.height);
        }
        for (int i = 0; i < avatar.height / Dodger.scale; i++) {
            g.drawLine(0, i * avatar.scale, avatar.width, i * avatar.scale);
        }//end board/grid

        g.setColor(Color.BLACK); //avatar
        g.fillRect(avatar.head.x, avatar.head.y, Dodger.scale, Dodger.scale);
        g.setColor(Color.GREEN);
        g.fillRect(avatar.head.x + 2, avatar.head.y + 2, Dodger.scale - 4, Dodger.scale - 4);
        //end avatar

        
        ArrayList<Point> cherries = avatar.Cherries;
        for (int i = 0; i < cherries.size(); i++) {
        g.setColor(Color.RED); //cherry
        g.fillOval(cherries.get(i).x, cherries.get(i).y, Dodger.scale, Dodger.scale);
        g.setColor(Color.GREEN);
        g.drawArc(cherries.get(i).x + avatar.scale / 2, cherries.get(i).y - avatar.scale / 4, avatar.scale / 2, avatar.scale / 2, 180, -90);
        
        }//end cherry

       
        //end bomb    
        //moving bomb
        ArrayList<Point> movingbomb = avatar.MovingBomb;
        for (int i = 0; i < movingbomb.size(); i++) {
            g.setColor(Color.BLACK);
            g.fillOval(movingbomb.get(i).x, movingbomb.get(i).y, Dodger.scale, Dodger.scale);
            g.setColor(Color.GRAY);
            g.drawArc(movingbomb.get(i).x + avatar.scale / 2, movingbomb.get(i).y - avatar.scale / 4, avatar.scale / 2, avatar.scale / 2, 180, -90);
        }

        //end moving bomb    
        g.setColor(Color.BLACK); //score display
        String string = "Score: " + avatar.score + " Points";
//        String string2 = "\n" + "Length: " + avatar.taillength;
        g.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        g.drawString(string, 70, 20);
//        g.drawString(string2, 365, 40);
        if (avatar.over) { //game over display
            
            g.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
            g.setColor(Color.RED);
            g.drawString("Game Over", 70, 180);
           g.fillRect(avatar.head.x, avatar.head.y, Dodger.scale, Dodger.scale);

        }
    }

}
