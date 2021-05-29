import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakePlayer extends Snake implements KeyListener
{

    public SnakePlayer(Board board)
    {
        super(board);
        color = Color.green;
    }

    @Override
    protected void control()
    {
    }

    @Override
    protected void dead()
    {
        game.finish();
    }

    @Override
    protected void awake()
    {
        super.awake();
        game.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_W && direction.y != 1)
            MoveUp();
        else if (e.getKeyCode() == KeyEvent.VK_S && direction.y != -1)
            MoveDown();
        else if (e.getKeyCode() == KeyEvent.VK_D && direction.x != -1)
            MoveRight();
        else if (e.getKeyCode() == KeyEvent.VK_A && direction.x != 1)
            ModeLeft();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    }

    @Override
    protected void render(Graphics2D g)
    {
        Font font = new Font("Arial", Font.PLAIN, 18);
        g.setFont(font);
        g.setColor(color);
        g.drawString("Player: " + points, 10, Gameplay.HEIGHT_OF_WINDOW-10);
    }
}
