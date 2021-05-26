public class FruitGenerator extends GameObject 
{
    private Board board;
    
    private int activeFruits = 0;
    private float secondsBetweenFruits;
    private int maxFruits;


    public FruitGenerator(Board board, int maxFruits, float secondsBetweenFruits)
    {
        this.board = board;
        this.maxFruits = maxFruits;
        this.secondsBetweenFruits = secondsBetweenFruits;
    }

    //todo To poleci do kosza, na razie jest tylko do debugu. Nie zalecam używać bo nie sprawdza czy cell jest pusty
    public Fruit SpawnNewFruit(int x, int y)
    {
        Cell emptyCell = board.GetCell(x, y);
        Fruit fruit = new Fruit(emptyCell,FruitType.Apple, this);
        emptyCell.content = fruit;
        game.initialize(fruit);
        activeFruits++;
        return fruit;
    }

    public void DecreaseCounter()
    {
        activeFruits--;
    }

    private void SpawnNewFruit()
    {
        Cell emptyCell = board.GetRandomEptyCell();
        Fruit fruit = new Fruit(emptyCell,FruitType.Apple, this);
        emptyCell.content = fruit;
        game.initialize(fruit);
        activeFruits++;
    }

    @Override
    protected void awake() 
    {

    }

    @Override
    protected void start() 
    {
        setTimer(secondsBetweenFruits * 1000);
    }

    @Override
    protected void update() {
        if (timerClockDown()) {
            setTimer(secondsBetweenFruits * 1000);
            if(activeFruits < maxFruits)
            {
                SpawnNewFruit();
            }

        }
    }
}