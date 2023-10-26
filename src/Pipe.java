import javax.swing.*;
import java.awt.*;

public class Pipe extends Sprite{
    private double  dx = 3;
    private boolean isBottom;

    public Pipe(int x, int y, boolean isBottom) {
        super(x, y);
        this.isBottom = isBottom;
    }

    public void setDx(double speed) {
        this.dx = speed;
    }

    public void loadImage() {
        ImageIcon ii = new ImageIcon("src/images/pipe.png");
        image = ii.getImage();
    }

    public void move() {
        x -= (int) dx;
    }
}
