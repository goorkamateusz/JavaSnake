/**
 * Class generate fruit on random position.
 */
public class FruitGenerator extends GameObject
{
    private Board board;
    private int activeFruits = 0;
    private float secondsBetweenFruits;
    private int maxFruits;

    /**
     * Constructor pass reference to board, set max fruits quantity and interwals of
     * time between new fruits spawn.
     */
    public FruitGenerator(Board board, int maxFruits, float secondsBetweenFruits)
    {
        this.board = board;
        this.maxFruits = maxFruits;
        this.secondsBetweenFruits = secondsBetweenFruits;
    }

    /**
     * Decrease counter.
     */
    public void DecreaseCounter()
    {
        activeFruits--;
    }

        /**
     * Spawn new fruit.
     */
    private void SpawnNewFruit()
    {
        Cell emptyCell = board.GetRandomEmptyCell();
        Fruit fruit = new Fruit(emptyCell, FruitType.Apple, this);
        emptyCell.content = fruit;
        game.initialize(fruit);
        activeFruits++;
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
