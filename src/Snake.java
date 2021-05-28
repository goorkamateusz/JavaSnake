import java.util.List;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends GameObject implements KeyListener
{
    private Board board;
    private List<SnakePart> body;
    private int newPartsNumber = 2;
    private Vector2D direction = new Vector2D(0, 1);

    private int points = 0; // todo licznik do punktów

    private final int TIMER_BASE_VALUE = 100;

    public Snake(Board board)
    {
        this.board = board;
    }

    public SnakePart Head()
    {
        return body.get(0);
    }

    public void MoveUp()
    {
        direction.set(0, -1);
    }

    public void MoveDown()
    {
        direction.set(0, 1);
    }

    public void ModeLeft()
    {
        direction.set(-1, 0);
    }

    public void MoveRight()
    {
        direction.set(1, 0);
    }

    private void IsAI()
    {
        Pathfinding pathfinding = new Pathfinding();
        var xd = pathfinding.A_Star(Head().position, new Vector2D(5,5), board);
    }

    //todo
    private boolean IsColliding(Cell nextCell)
    {
            // Jeśli ściana to popełnij seppuku
            if (nextCell instanceof Wall)
            {
                game.destroy(this);
                return true;
            }

            // Jeśli snake part to popełnij seppuku
            if(nextCell.content instanceof SnakePart)
            {
                game.destroy(this);
                return true;
            }

            // Jeśli owocek dodaj punkty i dostań ogon
            if (nextCell.content instanceof Fruit)
            {
                // Tutaj bym chciał zrobić casta ale nie umiem x.x
                // Fruit fruit = Fruit.cast(nextCell.content);

                newPartsNumber++;
                game.destroy(nextCell.content);
                
            }

            //Jeśli żaba to ją zjedz
            if(nextCell.content instanceof Frog)
            {
                newPartsNumber++;
                game.destroy(nextCell.content);
            }

            return false;
    }

    @Override
    protected void awake()
    {
        body = new ArrayList<SnakePart>();

        var emptyCell = board.GetRandomEmptyCell();
        SnakePart head = new SnakePart(emptyCell.position);
        body.add(head);
        board.GetCell(emptyCell.position).content = head;
        game.initialize(head);

        game.addKeyListener(this);
    }

    @Override
    protected void start()
    {
        // todo Dopytać czemy jak się da tutaj to co jest w Awake to się wywala?
        setTimer(TIMER_BASE_VALUE);
    }

    @Override
    protected void update()
    {
        if (timerClockDown())
        {
            setTimer(TIMER_BASE_VALUE);

            Vector2D lastPosition = new Vector2D(body.get(0).position);
            board.GetCell(lastPosition).content = null;

            SnakePart head = body.get(0);
            Vector2D newPosition = head.position.clone();
            newPosition.add(direction);
            Cell nextCell = board.GetCell(newPosition);

            if(IsColliding(nextCell))
                return;

            head.position.add(direction);
            board.GetCell(head.position).content = head;

            for (int i = 1; i < body.size(); i++)
            {
                Vector2D tmp = new Vector2D(body.get(i).position);
                board.GetCell(tmp).content = null;

                SnakePart part = body.get(i);
                part.position.set(lastPosition);
                board.GetCell(part.position).content = part;

                lastPosition = tmp;
            }

            if (newPartsNumber > 0)
            {
                SnakePart newPart = new SnakePart(lastPosition);
                body.add(newPart);
                board.GetCell(lastPosition).content = newPart;
                game.initialize(newPart);
                newPartsNumber--;
            }
        }
    }

    @Override
    protected void onDestroy()
    {
        // todo usuwać każdy segment snake osobno ale nie umiem tego zgrać z
        // game.destroy
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
}
