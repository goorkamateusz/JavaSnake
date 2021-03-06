import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;

/**
 * Main class of game.
 * Responsibility: main loop, game objects and window.
 */
public abstract class GameBase implements Runnable
{
	public static final int DEFAULT_WIDTH_OF_WINDOW = 1000;
	public static final int DEFAULT_HEIGHT_OF_WINDOW = 700;

	public final long DESIRED_FPS = 60;
	public final long DESIRED_DELTA_LOOP = (1000 * 1000 * 1000) / DESIRED_FPS;

	private List<GameObject> gameObjects = new ArrayList<GameObject>();
	private List<GameObject> newObjects = new ArrayList<GameObject>();
	private List<GameObject> objectsToDestroy = new ArrayList<GameObject>();

	protected JFrame frame;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;

	private boolean running = true;
	private float deltaTime = 0;

	/**
	 * Awake of game.
	 */
	public GameBase()
	{
		GameObject.game = this;
		awake();
	}

	/**
	 * Initialize gameobject on gameplay.
	 */
	public void initialize(GameObject gameObject)
	{
		gameObject.awake();
		newObjects.add(gameObject);
	}

	/**
	 * Destroy gameobject and invoke OnDestroy().
	 */
	public void destroy(GameObject gameObject)
	{
		objectsToDestroy.add(gameObject);
	}

	/**
	 * Add new key listener.
	 * @param listener - new key listener.
	 */
	public void addKeyListener(KeyListener listener)
	{
		canvas.addKeyListener(listener);
	}

	/**
	 * Main loop of game.
	 */
	public void run()
	{
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		start();

		while (running)
		{
			beginLoopTime = System.nanoTime();

			addNewObjects();
			destroyObjects();
			render();

			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			deltaTime = (currentUpdateTime - lastUpdateTime) / 1000000;

			update();

			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			if (deltaLoop < DESIRED_DELTA_LOOP)
			{
				try
				{
					Thread.sleep((DESIRED_DELTA_LOOP - deltaLoop) / 1000000);
				}
				catch (InterruptedException e)
				{
					// Do nothing
				}
			}
		}

		onDestroy();
	}

	/**
	 * Stop and finish main loop.
	 */
	public void finish()
	{
		running = false;
	}

	/**
	 * Get gameobjects in gameplay.
	 */
	public List<GameObject> getGameObjects()
	{
		return gameObjects;
	}

	/**
	 * DeltaTime of current frame.
	 */
	protected float getDeltaTime()
	{
		return deltaTime;
	}

	/**
	 * @param widthOfWindow
	 * @param heightOfWindow
	 * @param title
	 */
	protected void openWindow(int widthOfWindow, int heightOfWindow, String title)
	{
		frame = new JFrame(title);

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(widthOfWindow, heightOfWindow));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, widthOfWindow, heightOfWindow);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();
	}

	/**
	 * Scene awake on open the game.
	 */
	protected void awakeScene()
	{
		openWindow(DEFAULT_WIDTH_OF_WINDOW, DEFAULT_HEIGHT_OF_WINDOW, "Game");
	}

	/**
	 * Invoke before main loop.
	 */
	protected void startScene()
	{
	}

	/**
	 * Update scene before game objects.
	 */
	protected void updateScene()
	{
	}

	/**
	 * Destroy scene after game objects.
	 */
	protected void onDestroyScene()
	{
	}

	/**
	 * Render scenes before game objects.
	 * @param g - Graphics2D
	 */
	protected void renderScene(Graphics2D g)
	{
	}

	/**
	 * Awake scene and game objects.
	 */
	private void awake()
	{
		awakeScene();
		for (GameObject gameObject : gameObjects)
			gameObject.awake();
	}

	/**
	 * Start scene and game objects.
	 */
	private void start()
	{
		startScene();
		for (GameObject gameObject : gameObjects)
			gameObject.start();
	}

	/**
	 * Update scene and game objects.
	 */
	private void update()
	{
		updateScene();
		for (GameObject gameObject : gameObjects)
			gameObject.update();
	}

	/**
	 * Destroy scene and game objects.
	 */
	private void onDestroy()
	{
		for (GameObject gameObject : gameObjects)
			gameObject.onDestroy();
		onDestroyScene();
	}

	/**
	 * Render scene and game objects.
	 * @param g
	 */
	private void render(Graphics2D g)
	{
		renderScene(g);
		for (GameObject gameObject : gameObjects)
			gameObject.render(g);
	}

	private void render()
	{
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, DEFAULT_WIDTH_OF_WINDOW, DEFAULT_HEIGHT_OF_WINDOW);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	private void addNewObjects()
	{
		for (GameObject newObject : newObjects)
		{
			gameObjects.add(newObject);
			newObject.start();
		}
		newObjects.clear();
	}

	private void destroyObjects()
	{
		for (GameObject gameObject : objectsToDestroy)
		{
			gameObject.onDestroy();
			gameObjects.remove(gameObject);
		}
		objectsToDestroy.clear();
	}
}
