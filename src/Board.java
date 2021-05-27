public class Board extends GameObject {
    public Cell[][] board = new Cell[Gameplay.CELLS_X][Gameplay.CELLS_Y];

    public Board() {
        SetBorders();
        FillWithEmpty();
    }

    public Cell GetCell(int x, int y) {
        if (x < 0 || Gameplay.CELLS_X < x || y < 0 || Gameplay.CELLS_Y < y)
            System.out.println("OUT OF RANGE!!!!!!!!!!!!!!!!!!!!!!!!! rzucił bym exception ale nie umiem :c");
        return board[x][y];
    }

    public Cell GetCell(Vector2D lastPosition) {
        return GetCell(lastPosition.x, lastPosition.y);
    }

    public Cell GetEmptyCell() {
        for (Cell[] row : board) {
            for (Cell cell : row) {
                if (IsEmpty(cell)) {
                    return cell;
                }
            }
        }
        return null;
    }

    public Cell GetRandomEptyCell() {
        while (true) {
            Cell cell = GetCell(Random.Range(0, Gameplay.CELLS_X - 1), Random.Range(0, Gameplay.CELLS_Y - 1));
            if (IsEmpty(cell))
                return cell;
        }

    }

    public Cell GetClosestEmptyCell(Cell cell) {
        if (0 <= cell.x + 1 && cell.x + 1 < Gameplay.CELLS_X && IsEmpty(board[cell.x + 1][cell.y]))
            return board[cell.x + 1][cell.y];
        if (0 <= cell.y + 1 && cell.y + 1 < Gameplay.CELLS_Y && IsEmpty(board[cell.x][cell.y + 1]))
            return board[cell.x][cell.y + 1];
        if (0 <= cell.x - 1 && cell.x - 1 < Gameplay.CELLS_X && IsEmpty(board[cell.x - 1][cell.y]))
            return board[cell.x - 1][cell.y];
        if (0 <= cell.y - 1 && cell.y - 1 < Gameplay.CELLS_Y && IsEmpty(board[cell.x][cell.y - 1]))
            return board[cell.x][cell.y - 1];
        return null;

    }

    private boolean IsEmpty(Cell cell) {
        if (!(cell instanceof Wall) && cell.content == null)
            return true;
        else
            return false;
    }

    private void SetBorders() {
        for (int i = 0; i < Gameplay.CELLS_X; i++) {
            board[i][0] = new Wall(i, 0);
            board[i][Gameplay.CELLS_Y - 1] = new Wall(i, Gameplay.CELLS_Y - 1);
        }
        for (int i = 0; i < Gameplay.CELLS_Y; i++) {
            board[0][i] = new Wall(0, i);
            board[Gameplay.CELLS_X - 1][i] = new Wall(Gameplay.CELLS_X - 1, i);
        }
    }

    private void FillWithEmpty() {
        for (int i = 0; i < Gameplay.CELLS_X; i++) {
            for (int j = 0; j < Gameplay.CELLS_Y; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new Cell(i, j);
                }
            }
        }
    }

    // @Override
    // protected void awake() {
    // for (Cell[] cells : board) {
    // for (Cell cell : cells) {
    // Gameplay.initialize(cell);
    // }
    // }
    // }
}