import java.awt.*;

public class GameObject
{
    protected static GameBase game;

    protected float getDeltaTime() {
        return game.getDeltaTime();
    }
    
    protected void awake() {}
    protected void start() {}
    protected void update() {}
    protected void onDestroy() {}
    protected void render(Graphics2D g) {}
}