import java.awt.Color;

public class TestSqrt extends Cell {

    private float sign = 1;

    @Override
    protected void awake() {
        color = new Color(Random.Range(0, 255), Random.Range(0, 255), Random.Range(0, 255));
    }

    @Override
    protected void start() {
        x = Random.Range(0, Gameplay.CELLS_X);
        y = Random.Range(0, Gameplay.CELLS_Y);
    }

    @Override
    protected void update() {
        if (timerClockDown())
        {
            setTimer(500);

            x += sign;
            if (x > Gameplay.CELLS_X)
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
}