import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

/**
 * Implementation of A* algorithm to find best path. Can be run on separate.
 * thread.
 */
public class Pathfinding implements Runnable
{
    public Vector2D StartingPosition;
    public Vector2D EndingPosition;
    public Board Board;
    public ArrayList<Vector2D> Result;

    private ArrayList<Node> open;
    private ArrayList<Node> closed;
    private ArrayList<Vector2D> path;

    /**
     * Start game button action.
     */
    public Pathfinding(Vector2D starting, Vector2D ending, Board board)
    {
        StartingPosition = starting;
        EndingPosition = ending;
        Board = board;
    }

    /**
     * Run the algorithm.
     */
    @Override
    public void run()
    {
        Result = A_Star(StartingPosition, EndingPosition, Board);
    }

    /**
     * Main algorithm.
     */
    public ArrayList<Vector2D> A_Star(Vector2D startingPosition, Vector2D endingPosition, Board board)
    {
        open = new ArrayList<Node>();
        closed = new ArrayList<Node>();
        path = new ArrayList<Vector2D>();
        Node startingNode = new Node(startingPosition, startingPosition, 0, 0);
        open.add(startingNode);

        while (open.size() > 0)
        {
            Node q = new Node(new Vector2D(), new Vector2D(), 1000000, 1000000);

            for (Node node : open)
            {
                if (node.gCost + node.hCost < q.gCost + q.hCost)
                    q = node;
            }

            open.remove(q);

            ArrayList<Node> Successors = new ArrayList<Node>();
            Vector2D up = q.position.clone();
            Vector2D down = q.position.clone();
            Vector2D right = q.position.clone();
            Vector2D left = q.position.clone();

            up.y += -1;
            down.y += 1;
            right.x += 1;
            left.x += -1;

            try
            {
                // Tutaj dodać sprawdzanie innych rzeczy np. obecność innego snake'a
                if (!(board.GetCell(up) instanceof Wall) && !(board.GetCell(up).content instanceof SnakePart))
                    Successors.add(new Node(up.clone(), q.position.clone(), Cost(startingPosition, up),
                            Cost(endingPosition, up)));
                if (!(board.GetCell(down) instanceof Wall) && !(board.GetCell(down).content instanceof SnakePart))
                    Successors.add(new Node(down.clone(), q.position.clone(), Cost(startingPosition, down),
                            Cost(endingPosition, down)));
                if (!(board.GetCell(right) instanceof Wall) && !(board.GetCell(right).content instanceof SnakePart))
                    Successors.add(new Node(right.clone(), q.position.clone(), Cost(startingPosition, right),
                            Cost(endingPosition, right)));
                if (!(board.GetCell(left) instanceof Wall) && !(board.GetCell(left).content instanceof SnakePart))
                    Successors.add(new Node(left.clone(), q.position.clone(), Cost(startingPosition, left),
                            Cost(endingPosition, left)));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {

            }

            for (Node node : Successors)
            {
                if (node.position.equalValue(endingPosition))
                {
                    path.add(node.position.clone());
                    path.add(node.parentPosition.clone());

                    for (Node qParent : closed)
                    {
                        if (qParent.position.equalValue(q.parentPosition))
                            PrintWay(qParent);
                    }

                    Collections.reverse(path);
                    open.clear();
                    closed.clear();
                    return path;
                }
            }

            for (Node node : Successors)
            {
                boolean isOnClosed = false;
                boolean isOnOpen = false;
                int i = 0;
                int j = 0;
                while (!isOnClosed && i < closed.size())
                {
                    if (closed.get(i).position.equalValue(node.position))
                        isOnClosed = true;
                    i++;
                }
                while (!isOnOpen && j < open.size())
                {
                    if (open.get(j).position.equalValue(node.position))
                        isOnOpen = true;
                    j++;
                }

                if (!isOnClosed)
                {
                    if (isOnOpen)
                    {
                        int fCost = node.gCost + node.hCost;

                        for (Node openNode : open)
                        {
                            if (openNode.position.equalValue(node.position) && openNode.gCost + openNode.hCost > fCost)
                            {
                                openNode.parentPosition = q.position.clone();
                                openNode.gCost = node.gCost;
                                openNode.hCost = node.hCost;
                            }
                        }
                    } else
                        open.add(node);
                }
            }
            closed.add(q);
        }
        return path;
    }

    /**
     * Calculation of cost for nodes
     */

    private int Cost(Vector2D startingPosition, Vector2D nodePosition)
    {
        int x = Math.abs(startingPosition.x - nodePosition.x);
        int y = Math.abs(startingPosition.y - nodePosition.y);
        return x + y;
    }

    /**
     * Recurency form an output path for algorithm
     */
    private void PrintWay(Node finishNode)
    {
        path.add(finishNode.position);
        if (!finishNode.position.equalValue(finishNode.parentPosition))
        {
            for (Node parent : closed)
            {
                if (parent.position.equalValue(finishNode.parentPosition))
                {
                    PrintWay(parent);
                }
            }
        }
    }
}
