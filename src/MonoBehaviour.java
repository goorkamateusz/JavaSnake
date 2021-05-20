import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public abstract class MonoBehaviour
{
    private static List<MonoBehaviour> gameObjects = new ArrayList<MonoBehaviour>();
    public static GameBase gameBase;

    protected MonoBehaviour() {
        addToScene();
    }

    public static List<MonoBehaviour> getGameObjects() {
        return gameObjects;
    }

    protected float getDeltaTime() {
        return gameBase.getDeltaTime();
    }
    
    protected void awake() {}
    protected void start() {}
    protected void update() {}
    protected void onDestroy() {}
    protected void render(Graphics2D g) {}

    private void addToScene() {
        gameObjects.add(this);
    }
}