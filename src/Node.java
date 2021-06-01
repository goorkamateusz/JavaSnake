/**
 * Store information about cells cost for A* pathfinding algorithm.
 */
public class Node
{
    public Vector2D position;
    public Vector2D parentPosition;
    public int gCost;
    public int hCost;

    /**
     * Basic constructor to set field.
     */
    public Node(Vector2D pos, Vector2D parentPos, int g, int h)
    {
        position = pos;
        parentPosition = parentPos;
        gCost = g;
        hCost = h;
    }
}
