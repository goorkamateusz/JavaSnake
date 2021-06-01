/**
 * Class to visualize enviroment in game.
 */
public class Board extends GameObject
{
    public Cell[][] board = new Cell[Gameplay.CELLS_X][Gameplay.CELLS_Y];

    /**
     * Constructor set borders of scene, spawn obstacles and fill empty spaces with
     * cells.
     */
    public Board(int obstaclesCount)
    {
        SetBorders();
        RandomObstacles(obstaclesCount);
        FillWithEmpty();
        Initialize();
    }

    /**
     * Return cell in position.
     */
    public Cell GetCell(int x, int y)
    {
        return board[x][y];
    }

    /**
     * Return cell in position.
     */
    public Cell GetCell(Vector2D lastPosition)
    {
        return GetCell(lastPosition.x, lastPosition.y);
    }

    /**
     * Return first empty cell starting from (0,0) else return null.
     */
    public Cell GetFirstEmptyCell()
    {
        for (Cell[] row : board)
            for (Cell cell : row)
                if (IsEmpty(cell))
                    return cell;
        return null;
    }

    /**
     * Return random empty cell on board. If there is non return null.
     */
    public Cell GetRandomEmptyCell()
    {
        while (true)
        {
            Cell cell = GetCell(Random.Range(0, Gameplay.CELLS_X - 1), Random.Range(0, Gameplay.CELLS_Y - 1));
            if (IsEmpty(cell))
                return cell;
        }
    }

    /**
     * Return empty cell closest to cell.
     */
    public Cell GetClosestEmptyCell(Cell cell)
    {
        if (0 <= cell.position.x + 1 && cell.position.x + 1 < Gameplay.CELLS_X
                && IsEmpty(board[cell.position.x + 1][cell.position.y]))
            return board[cell.position.x + 1][cell.position.y];
        if (0 <= cell.position.y + 1 && cell.position.y + 1 < Gameplay.CELLS_Y
                && IsEmpty(board[cell.position.x][cell.position.y + 1]))
            return board[cell.position.x][cell.position.y + 1];
        if (0 <= cell.position.x - 1 && cell.position.x - 1 < Gameplay.CELLS_X
                && IsEmpty(board[cell.position.x - 1][cell.position.y]))
            return board[cell.position.x - 1][cell.position.y];
        if (0 <= cell.position.y - 1 && cell.position.y - 1 < Gameplay.CELLS_Y
                && IsEmpty(board[cell.position.x][cell.position.y - 1]))
            return board[cell.position.x][cell.position.y - 1];
        return null;
    }

    /**
     * Return fruit closest to origin else return null.
     */
    public Cell GetClosestFruit(Cell origin)
    {
        int distance = 100000;
        Cell cellWithFruit = null;
        for (Cell[] cells : board)
        {
            for (Cell cell : cells)
            {
                if (cell.content instanceof Fruit)
                {
                    int temp = origin.position.clone().distanceSq(cell.position.clone());
                    if (temp < distance)
                    {
                        distance = temp;
                        cellWithFruit = cell;
                    }
                }
            }
        }
        return cellWithFruit;
    }

    /**
     * Check if cell is not obstacle and is empty.
     */

    private boolean IsEmpty(Cell cell)
    {
        return !(cell instanceof Wall) && cell.content == null;
    }

    /**
     * Spawn Wall instead of Cell at borders of scene.
     */
    private void SetBorders()
    {
        for (int i = 0; i < Gameplay.CELLS_X; i++)
        {
            board[i][0] = new Wall(i, 0);
            board[i][Gameplay.CELLS_Y - 1] = new Wall(i, Gameplay.CELLS_Y - 1);
        }
        for (int i = 0; i < Gameplay.CELLS_Y; i++)
        {
            board[0][i] = new Wall(0, i);
            board[Gameplay.CELLS_X - 1][i] = new Wall(Gameplay.CELLS_X - 1, i);
        }
    }

    /**
     * Spawn random Wall instead of Cell at scene.
     */
    private void RandomObstacles(int obstaclesCount)
    {
        for (int i = 0; i < obstaclesCount; i++)
        {
            Vector2D position = new Vector2D(Random.Range(0, Gameplay.CELLS_X - 1),
                    Random.Range(0, Gameplay.CELLS_Y - 1));
            if (GetCell(position.x, position.y) != null)
                i--;
            else
                board[position.x][position.y] = new Wall(position.x, position.y);
        }
    }

    /**
     * Fill board with empty cell.
     */
    private void FillWithEmpty()
    {
        for (int i = 0; i < Gameplay.CELLS_X; i++)
            for (int j = 0; j < Gameplay.CELLS_Y; j++)
                if (board[i][j] == null)
                {
                    board[i][j] = new Cell(i, j);
                }
    }

    /**
     * Initialize every object on Board.
     */
    private void Initialize()
    {
        for (Cell[] cells : board)
        {
            for (Cell cell : cells)
            {
                game.initialize(cell);
            }
        }
    }
}
