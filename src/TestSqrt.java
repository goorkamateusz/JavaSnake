import java.awt.Graphics2D;

public class TestSqrt extends GameObject {

    private float x;
    private float sign = 1;
    private int max = 600;
    private int min = -50;

    @Override
    protected void start() {
        x = (float) Math.floor(Math.random()*(max-min+1)+min);
    }

    @Override
    protected void update() {
        x += sign * getDeltaTime() * 0.5;
        if (x > 600)
        {
            sign = -1;
            gameBase.initialize(new TestSqrt());

            if (gameBase.getGameObjects().size() > 5)
                gameBase.finish();

        }
        if (x < 0)
        {
            sign = 1;
        }
    }

    @Override
    protected void render(Graphics2D g) {
        g.fillRect((int) x, 0, 200, 200);
    }
}