import javax.swing.*;

/**
 * Main UI window of game.
 */
class MainWindow
{
    /**
     * Width of window
     */
    private static final int WIDTH_OF_WINDOW = 250;

    /**
     * Height of window
     */
    private static final int HEIGHT_OF_WINDOW = WIDTH_OF_WINDOW;

    /**
     * Button height
     */
    private static final int BUTTON_HEIGHT = 60;

    /**
     * Button width
     */
    private static final int BUTTON_WIDTH = 100;

    /**
     * Main frame of window
     */
    private JFrame mainFrame;

    /**
     * Start button
     */
    private JButton startButton;

    /**
     * MainWindow constructor
     */
    public MainWindow()
    {
        mainFrame = new JFrame();
        mainFrame.setSize(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        startButton = new JButton("Start");
        startButton.setBounds((WIDTH_OF_WINDOW - BUTTON_WIDTH) / 2, (HEIGHT_OF_WINDOW - BUTTON_HEIGHT * 2) / 2,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        startButton.addActionListener(e ->
        {
            startGame();
        });
        mainFrame.add(startButton);
    }

    /**
     * Start game button action.
     */
    public void startGame()
    {
        startButton.setVisible(false);
        new Thread(new Gameplay()).start();
    }
}
