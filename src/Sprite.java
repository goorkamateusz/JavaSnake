import java.awt.*;

public class Sprite extends GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    protected void loadImage(String name) {
        Toolkit t = Toolkit.getDefaultToolkit();
        image = t.getImage(name);
    }

    @Override
    protected void render(Graphics2D g) {
        // todo nie działa na razie
        g.drawImage(image, x, y, null);
    }
}
