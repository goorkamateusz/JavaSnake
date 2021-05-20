import java.awt.Graphics2D;

public class TestSqrt extends MonoBehaviour {

    private float x;

    @Override
    protected void start() {
        x = -50;
    }

    @Override
    protected void update() {
        x += getDeltaTime() * 0.2;
        if (x > 600)
            x = 0;
    }

    @Override
    protected void render(Graphics2D g) {
        g.fillRect((int) x, 0, 200, 200);
    }
}