public class Board extends GameObject {
    public Cell[][] board = new Cell[Gameplay.CELLS_X][Gameplay.CELLS_Y];

    public Board() {
        SetBorders();
        FillWithEmpty();
    }

    public Cell GetCell(int x, int y) {
        return board[x][y];
    }

    public Cell GetCell(Vector2D lastPosition) {
        return GetCell(lastPosition.x, lastPosition.y);
    }

    public Cell GetFirstEmptyCell() {
        for (Cell[] row : board)
            for (Cell cell : row)
                if (IsEmpty(cell))
                    return cell;
        return null;
    }

    public Cell GetRandomEmptyCell() {
        while (true) {
            Cell cell = GetCell(Random.Range(0, Gameplay.CELLS_X - 1), Random.Range(0, Gameplay.CELLS_Y - 1));
            if (IsEmpty(cell))
                return cell;
        }
    }

    public Cell GetClosestEmptyCell(Cell cell) {
        if (0 <= cell.position.x + 1 && cell.position.x + 1 < Gameplay.CELLS_X && IsEmpty(board[cell.position.x + 1][cell.position.y]))
            return board[cell.position.x + 1][cell.position.y];
        if (0 <= cell.position.y + 1 && cell.position.y + 1 < Gameplay.CELLS_Y && IsEmpty(board[cell.position.x][cell.position.y + 1]))
            return board[cell.position.x][cell.position.y + 1];
        if (0 <= cell.position.x - 1 && cell.position.x - 1 < Gameplay.CELLS_X && IsEmpty(board[cell.position.x - 1][cell.position.y]))
            return board[cell.position.x - 1][cell.position.y];
        if (0 <= cell.position.y - 1 && cell.position.y - 1 < Gameplay.CELLS_Y && IsEmpty(board[cell.position.x][cell.position.y - 1]))
            return board[cell.position.x][cell.position.y - 1];
        return null;

    }

    private boolean IsEmpty(Cell cell) {
        return !(cell instanceof Wall) && cell.content == null;
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
        for (int i = 0; i < Gameplay.CELLS_X; i++)
            for (int j = 0; j < Gameplay.CELLS_Y; j++)
                if (board[i][j] == null) {
                    board[i][j] = new Cell(i, j);
                }
    }
}
