import java.awt.Color;

public class TestSqrt extends Rectangle {

    private float sign = 1;

    @Override
    protected void awake() {
        width = 40;
        height = 40;
        color = new Color(Random.Range(0, 255), Random.Range(0, 255), Random.Range(0, 255));
    }

    @Override
    protected void start() {
        x = Random.Range(0, GameBase.WIDTH_OF_WINDOW);
        y = Random.Range(0, GameBase.HEIGHT_OF_WINDOW);
    }

    @Override
    protected void update() {
        x += sign * getDeltaTime() * 0.5;
        if (x > GameBase.WIDTH_OF_WINDOW)
        {
            sign = -1;
            game.initialize(new TestSqrt());
        }
        if (x < 0)
        {
            game.destroy(this);
            sign = 1;
        }
    }
}