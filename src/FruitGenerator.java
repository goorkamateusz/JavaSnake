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

    public void DecreaseCounter()
    {
        activeFruits--;
    }

    private void SpawnNewFruit()
    {
        Cell emptyCell = board.GetRandomEmptyCell();
        Fruit fruit = new Fruit(emptyCell, FruitType.Apple, this);
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
    protected void update()
    {
        if (timerClockDown())
        {
            setTimer(secondsBetweenFruits * 1000);

            if (activeFruits < maxFruits)
                SpawnNewFruit();
        }
    }
}
