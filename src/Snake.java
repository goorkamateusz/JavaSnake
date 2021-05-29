import java.util.List;

import java.util.ArrayList;
import java.awt.Color;

public abstract class Snake extends GameObject
{
    protected Board board;
    protected List<SnakePart> body;
    protected int newPartsNumber = 2;
    protected Vector2D direction = new Vector2D(0, 1);
    protected Color color;

    protected int points = 0; // todo licznik do punktów

    protected final int TIMER_BASE_VALUE = 200;

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

    protected void control()
    {
    }

    protected void dead()
    {
        for (SnakePart snakePart : body) {
            game.destroy(snakePart);
        }
        game.destroy(this);
    }

    @Override
    protected void awake()
    {
        body = new ArrayList<SnakePart>();

        var emptyCell = board.GetRandomEmptyCell();
        SnakePart head = new SnakePart(emptyCell.position, color);
        body.add(head);
        board.GetCell(emptyCell.position).content = head;
        game.initialize(head);

        if (newPartsNumber > 0)
        {
            Cell closestEmptyCell = board
                    .GetClosestEmptyCell(board.GetCell(body.get(body.size() - 1).position.clone()));
            SnakePart newPart = new SnakePart(closestEmptyCell.position.clone(), color);
            body.add(newPart);
            board.GetCell(closestEmptyCell.position.clone()).content = newPart;
            game.initialize(newPart);
            newPartsNumber--;
        }
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

            if (IsColliding(nextCell))
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

            control();

            if (newPartsNumber > 0)
            {
                SnakePart newPart = new SnakePart(lastPosition, color);
                body.add(newPart);
                board.GetCell(lastPosition).content = newPart;
                game.initialize(newPart);
                newPartsNumber--;
            }
        }
    }

    @Override
    protected void onDestroy() {
        body.clear();
    }

    // todo
    private boolean IsColliding(Cell nextCell)
    {
        // Jeśli ściana to popełnij seppuku
        if (nextCell instanceof Wall)
        {
            dead();
            return true;
        }

        // Jeśli snake part to popełnij seppuku
        if (nextCell.content instanceof SnakePart)
        {
            dead();
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

        // Jeśli żaba to ją zjedz
        if (nextCell.content instanceof Frog)
        {
            newPartsNumber++;
            game.destroy(nextCell.content);
        }

        return false;
    }
}
