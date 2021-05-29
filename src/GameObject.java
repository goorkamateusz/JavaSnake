import java.awt.*;

/**
 * Game objects class, base of objects on the scenes.
 */
public class GameObject
{
    /**
     * Reference to gameplay
     */
    protected static GameBase game;

    /**
     * Timer value
     */
    protected float timer;

    /**
     * Like Time.deltaTime
     */
    protected float getDeltaTime()
    {
        return game.getDeltaTime();
    }

    /**
     * Set timer in milliseconds
     */
    protected void setTimer(float milliseconds)
    {
        timer = milliseconds;
    }

    /**
     * Clock down timer
     */
    protected boolean timerClockDown()
    {
        return (timer -= getDeltaTime()) < 0;
    }

    /**
     * Like unity Awake (invoke on create of object or create gameplay)
     */
    protected void awake()
    {
    }

    /**
     * Like unity Start (invoke on first frame of object like or before begin main
     * loop)
     */
    protected void start()
    {
    }

    /**
     * Like unity Update (invoke after render on each frames)
     */
    protected void update()
    {
    }

    /**
     * Like unity OnDestroy (invoke after destroy object or on end of game)
     */
    protected void onDestroy()
    {
    }

    /**
     * Method who render a object on screen (invoke on begin of each frames)
     */
    protected void render(Graphics2D g)
    {
    }
}
