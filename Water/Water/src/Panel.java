import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, MouseListener{

    // SYSTEM SETTINGS
    int maxCol = 12;
    int maxRow = 20;
    int tileSize = 48;

    public final int screenWidth = maxCol*tileSize;
    public final int screenHeight = maxRow*tileSize;

    // SAND PILE
    public SandPile sandPile;

    // MOUSE LISTERNER
    MouseListener mListener;

    // THREAD
    Thread thread;

    //FPS
    final int FPS = 15;
    
    public Panel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(false);
        this.setDoubleBuffered(true);

        this.addMouseListener(this);

        sandPile = new SandPile(this);
    }

    public void start(){
        thread = new Thread();
        thread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 1s in nano divided by 60
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        @SuppressWarnings("unused")
        long timer = 0; // is used in while loop

        while (thread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime); // add past time to timer 
            lastTime = currentTime;


            if(delta >= 1){
                sandPile.update();
                repaint();           //call paintComponent (JPanel implementation)
                delta--;
            }
        }
    }

    //MOUSE MANAGEMENT

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        sandPile.addSand((int) e.getX()/tileSize,(int) e.getY()/tileSize); // adds sand to clicked
        repaint();
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

    // PAINTING

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        sandPile.draw(g2);
    }


    
}
