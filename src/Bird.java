import javax.swing.*;
import java.awt.event.KeyEvent;

public class Bird extends Sprite {
    private double  dy = 1;
    private double  gravity = 0.05;
    private boolean isJumping = false;

    public Bird() {
        super(40, 60);
    }

    public double getDy() {
        return dy;
    }


    public void loadImage() {
        ImageIcon ii = new ImageIcon("src/images/bird.png");
        image = ii.getImage();
    }

    public void move() {
        dy += gravity;
        y += (int) dy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && !isJumping) {
            dy = -5;
            isJumping = true;
        }
    }

    public void keyReleased(KeyEvent e) throws InterruptedException {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            dy = 1; // When space is released, start moving downwards again
            isJumping = false;
        }
    }
}