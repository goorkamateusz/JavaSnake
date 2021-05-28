public class Vector2D
{
    public int x;
    public int y;

    public Vector2D()
    {
    }

    public Vector2D(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D v)
    {
        set(v);
    }

    public void set(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2D v)
    {
        this.x = v.x;
        this.y = v.y;
    }

    public void setZero()
    {
        x = 0;
        y = 0;
    }

    public int[] getComponents()
    {
        return new int[]
        {
                x, y };
    }

    public double getLength()
    {
        return Math.sqrt(getLengthSqrt());
    }

    public int getLengthSqrt()
    {
        return (x * x + y * y);
    }

    public int distanceSq(int vx, int vy)
    {
        vx -= x;
        vy -= y;
        return (vx * vx + vy * vy);
    }

    public int distanceSq(Vector2D v)
    {
        int vx = v.x - this.x;
        int vy = v.y - this.y;
        return (vx * vx + vy * vy);
    }

    public void normalize()
    {
        double magnitude = getLength();
        x /= magnitude;
        y /= magnitude;
    }

    public Vector2D getNormalized()
    {
        double magnitude = getLength();
        return new Vector2D((int) (x / magnitude), (int) (y / magnitude));
    }

    public void add(Vector2D v)
    {
        this.x += v.x;
        this.y += v.y;
    }

    public void add(int vx, int vy)
    {
        this.x += vx;
        this.y += vy;
    }

    public static Vector2D add(Vector2D v1, Vector2D v2)
    {
        return new Vector2D(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2D getAdded(Vector2D v)
    {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    public void subtract(Vector2D v)
    {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void subtract(int vx, int vy)
    {
        this.x -= vx;
        this.y -= vy;
    }

    public static Vector2D subtract(Vector2D v1, Vector2D v2)
    {
        return new Vector2D(v1.x - v2.x, v1.y - v2.y);
    }

    public Vector2D getSubtracted(Vector2D v)
    {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    public void multiply(int scalar)
    {
        x *= scalar;
        y *= scalar;
    }

    public Vector2D getMultiplied(int scalar)
    {
        return new Vector2D(x * scalar, y * scalar);
    }

    public void divide(int scalar)
    {
        x /= scalar;
        y /= scalar;
    }

    public Vector2D getDivided(int scalar)
    {
        return new Vector2D(x / scalar, y / scalar);
    }

    public Vector2D getPerp()
    {
        return new Vector2D(-y, x);
    }

    public int dot(Vector2D v)
    {
        return (this.x * v.x + this.y * v.y);
    }

    public int dot(int vx, int vy)
    {
        return (this.x * vx + this.y * vy);
    }

    public static int dot(Vector2D v1, Vector2D v2)
    {
        return v1.x * v2.x + v1.y * v2.y;
    }

    public int cross(Vector2D v)
    {
        return (this.x * v.y - this.y * v.x);
    }

    public int cross(int vx, int vy)
    {
        return (this.x * vy - this.y * vx);
    }

    public static int cross(Vector2D v1, Vector2D v2)
    {
        return (v1.x * v2.y - v1.y * v2.x);
    }

    public void reverse()
    {
        x = -x;
        y = -y;
    }

    public Vector2D getReversed()
    {
        return new Vector2D(-x, -y);
    }

    @Override
    public Vector2D clone()
    {
        return new Vector2D(x, y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj instanceof Vector2D)
        {
            Vector2D v = (Vector2D) obj;
            return (x == v.x) && (y == v.y);
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Vector2d[" + x + ", " + y + "]";
    }
}
