import javax.swing.*;

class MainWindow
{
    /**
     * Width of window
     */
    private static final int WidthOfWindow = 200;

    /**
     * Height of window
     */
    private static final int HeightOfWindow = WidthOfWindow;

    /**
     * Button height
     */
    private static final int ButtonHeight = 60;

    /**
     * Button width
     */
    private static final int ButtonWidth = 100;

    /**
     * Main frame of window
     */
    private JFrame mainFrame;

    /**
     * Game
     */
    private Gameplay game;

    /**
     * Start button
     */
    private JButton startButton;

    /**
     * MainWindow constructor
     */
    MainWindow()
    {
        mainFrame = new JFrame();
        mainFrame.setSize(WidthOfWindow, HeightOfWindow);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        startButton = new JButton("Start");
        startButton.setBounds((WidthOfWindow - ButtonWidth) / 2, (HeightOfWindow - ButtonHeight * 2) / 2, ButtonWidth,
                ButtonHeight);
        startButton.addActionListener(e ->
        {
            startGame();
        });
        mainFrame.add(startButton);
    }

    public void startGame()
    {
        startButton.setVisible(false);
        game = new Gameplay();
        new Thread(game).start();
    }
}
