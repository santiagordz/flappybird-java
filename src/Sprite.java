import java.awt.*;
import javax.swing.ImageIcon;

abstract class Sprite {

    protected int x, y, width, height;
    protected boolean visible;
    protected Image image;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = true;
        loadImage();
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    abstract void loadImage();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}