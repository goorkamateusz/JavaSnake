import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Gameplay extends GameBase {

    private float x;

    /**
     * SnakeGame constructor
     */
    public Gameplay(JFrame windowFrame) {
        OpenWindow();
    }

    @Override
    protected void awake() {

    }

    @Override
    protected void start() {

    }

    @Override
    protected void onDestroy() {

    }

    @Override
    protected void update() {
        x += getDeltaTime() * 0.2;
    }

    @Override
    protected void render(Graphics2D g) {
        g.fillRect((int) x, 0, 200, 200);

    }
}
