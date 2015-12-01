import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Image;

public class KLDrawProj extends JApplet implements KeyListener, MouseListener, MouseMotionListener
{
    int m, n, c, rad, oldm, oldn, newm, newn;
    boolean cs, drawing, UI, rect, line, freedraw, circle;
    Color purple;
        
    public void init()
    {
        setFocusable(true);
        resize(800, 600);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        m = -10; 
        n = -10;
        oldm = -10;
        oldn = -10;
        newm = -10;
        newn = -10;
       
        c = 1;
        rad = 10;
        cs = false;
        drawing = false;
        UI = true;
        
        rect = false;
        line = false;
        freedraw = true;
        circle = false;
        
        purple = new Color(153,50,204);
        
    }
    public void paint (Graphics g) 
    {
        showStatus("" + m +  " " + n);
        
        
        
        if(cs==true){
            g.setColor(Color.white);
            g.fillRect(0,0,800,600);
            cs=false;
        }
       
        if(UI == true){
            
            g.setColor(Color.black);
            
            g.drawRect(80,0,40,40);
            g.drawString("Pen", 85, 25);
            
            g.drawRect(120,0,40,40);
            g.drawRect(125,5,30,30);
            
            g.drawRect(160,0,40,40);
            g.drawOval(165,5,30,30); //draw oval button
            
            g.drawRect(200,0,40,40);
            g.drawLine(205,35, 235, 5); //draw line button
            
            
            g.drawRect(260,0,40,40); //'eraser'
            g.drawString("Erase",265,25);
            
            g.setColor(Color.red);
            g.fillRect(300,0,40,40); //color palette red
        
            g.setColor(Color.orange);
            g.fillRect(340,0,40,40); //color palette orange
        
            g.setColor(Color.yellow);
            g.fillRect(380,0,40,40); //color palette yellow
        
            g.setColor(Color.green);
            g.fillRect(420,0,40,40); //color palette green
        
            g.setColor(Color.blue);
            g.fillRect(460,0,40,40); //color palette blue
        
            g.setColor(purple);
            g.fillRect(500,0,40,40); //color palette purple
        
            
        
            g.setColor(Color.black);
            g.drawString("Brush size:", 550, 25);
            g.drawRect(620,0,40,40);
            g.drawString("+",635,25);
            g.drawRect(660,0,40,40);
            g.drawString("-",675,25); //brush size interface
        
            g.drawRect(720,0,70,40);
            g.drawString("Clear", 740,25); //clear button
        
            g.drawLine(0,40,800,40); //border of canvas vs interface
            UI = false;
        }
        
        
        if(c==0){
            g.setColor(Color.black);
            g.drawRect(262,2,36,36);
            g.setColor(Color.white); //turn on eraser / white brush
            
            
        }
            
        if(c==1){
            g.setColor(Color.black);
            g.drawRect(302,2,36,36);
            g.setColor(Color.red); //set red in paint
            
        }
        if(c==2){
            g.setColor(Color.black);
            g.drawRect(342,2,36,36);
            g.setColor(Color.orange); //set orange in paint
            
        }
        if(c==3){
            g.setColor(Color.black);
            g.drawRect(382,2,36,36);
            g.setColor(Color.yellow); //set yellow in paint
        }
        if(c==4){
            g.setColor(Color.black);
            g.drawRect(422,2,36,36);
            g.setColor(Color.green); //set green in paint
        }
        if(c==5){
            g.setColor(Color.black);
            g.drawRect(462,2,36,36);
            g.setColor(Color.blue); //set blue in paint
        }
        if(c==6){
            g.setColor(Color.black);
            g.drawRect(502,2,36,36);
            g.setColor(purple); //set purple in paint
        }
        if(line == true){
            g.drawRect(202,2,36,36);
        }
        
        
        if(rad < 5){
            rad = 5; //dont let brush disappear
        }
        if(rad > 100){
            rad = 100; //dont let brush get huge
        }
        if((n > 45) && (freedraw == true)){
            g.fillOval(m-5,n-5,rad,rad); //limit to canvas drawing only
        }
        if((n > 45) && (line == true)){
            g.drawLine(oldm, oldn, newm, newn);
        }
        
    }
    
    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
        drawing = false;
        if(line == true){
            newm = e.getX();
            newn = e.getY();
        }
        repaint();
    }
    public void mousePressed(MouseEvent e) {
        m = e.getX();
        n = e.getY();
        oldm = m;
        oldn = n;
    
    }
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {
        drawing = true;
        m = e.getX();
        n = e.getY();
        if(freedraw==true){
            repaint();
        }
        if(line==true){
            newm = m;
            newn = n;
            repaint();
        }
    }
    public void mouseClicked(MouseEvent e) {
        m = e.getX();
        n = e.getY();
        if((m > 80) && (m < 120) && (n < 45)){
            freedraw = true;
            rect = false;
            circle = false;
            line = false;
        }
        if((m > 120) && (m < 160) && (n < 45)){
            rect = true;
            circle = false;
            freedraw = false;
            line = false;
        }
        if((m > 160) && (m < 200) && (n < 45)){
            circle = true;
            rect = false;
            freedraw = false;
            line = false;
        }
        if((m > 200) && (m < 240) && (n < 45)){
            line = true;
            rect = false;
            freedraw = false;
            circle = false;
        }
        
        if((m > 260) && (m < 300) && (n < 45)){
            c= 0;
            rad = rad + 20;
            UI = true;
        }
        if((m > 300) && (m < 340) && (n < 45)){
            c = 1;
            UI = true;
        } // set red
        if((m > 340) && (m < 380) && (n < 45)){
            c = 2;
            UI = true;
        } // set orange
        if((m > 380) && (m < 420) && (n < 45)){
            c = 3;
            UI = true;
        } // set yellow 
        if((m > 420) && (m < 460) && (n < 45)){
            c = 4;
            UI = true;
        } // set green
        if((m > 460) && (m < 500) && (n < 45)){
            c = 5;
            UI = true;
        } // set blue
        if((m > 500) && (m < 540) && (n < 45)){
            c = 6;
            UI = true;
        } // set violet
        
        if((m > 620) && (m < 660) && (n < 45)){
            rad = rad + 5;
        } //increase brush size 
        if((m > 660) && (m < 700) && (n < 45)){
            rad = rad - 5;
        } //decrease brush size
        
        if((m > 720) && (m < 790) && (n < 45)){
            cs = true;
            UI = true;
            
             } //clear screen button click
        repaint();
    }
}