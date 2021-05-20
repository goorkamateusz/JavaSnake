import java.awt.Graphics2D;

public abstract class GameObject
{
    protected static GameBase gameBase;

    protected float getDeltaTime() {
        return gameBase.getDeltaTime();
    }
    
    protected void awake() {}
    protected void start() {}
    protected void update() {}
    protected void onDestroy() {}
    protected void render(Graphics2D g) {}
}