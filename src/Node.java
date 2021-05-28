public class Node
{
    public Vector2D position;
    public Vector2D parentPosition;
    public int gCost;
    public int hCost;

    public Node()
    {
        position = new Vector2D();
        parentPosition = new Vector2D();
        gCost = 1000000;
        hCost = 1000000;
    }
    public Node(Vector2D pos, Vector2D parentPos, int g, int h)
    {
        position = pos;
        parentPosition = parentPos;
        gCost = g;
        hCost = h;
    }
}