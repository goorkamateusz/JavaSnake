import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GameBase implements Runnable {

	final int WIDTH_OF_WINDOW = 1000;
	final int HEIGHT_OF_WINDOW = 700;

	final long DESIRED_FPS = 60;
	final long DESIRED_DELTA_LOOP = (1000 * 1000 * 1000) / DESIRED_FPS;

	private boolean running = true;

	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;

	private List<MonoBehaviour> gameObjects;

	private float deltaTime;

	public GameBase()
	{
		openWindow();

		MonoBehaviour.gameBase = this;
		gameObjects = MonoBehaviour.getGameObjects();

		awake();
	}

	public void run() {
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		start();

		while (running) {
			beginLoopTime = System.nanoTime();

			render();

			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			deltaTime = (currentUpdateTime - lastUpdateTime) / (1000 * 1000);

			update();

			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			if (deltaLoop > DESIRED_DELTA_LOOP) {
				// Do nothing. We are already late.
			}
			else {
				try {
					Thread.sleep((DESIRED_DELTA_LOOP - deltaLoop) / (1000 * 1000));
				}
				catch (InterruptedException e) {
					// Do nothing
				}
			}
		}

		onDestroy();
	}

	/**
	 * DeltaTime of current frame
	 */
	protected float getDeltaTime() {
		return deltaTime;
	}

	/**
	 * Awake method like Unity
	 */
	protected void awake() {
		for (MonoBehaviour gameObject : gameObjects)
			gameObject.awake();
	}

	/**
	 * Start method like Unity
	 */
	protected void start() {
		for (MonoBehaviour gameObject : gameObjects)
			gameObject.start();
	}

	/**
	 * Update method like Unity
	 */
	protected void update() {
		for (MonoBehaviour gameObject : gameObjects)
			gameObject.update();
	}

	/**
	 * OnDestroy method like Unity
	 */
	protected void onDestroy() {
		for (MonoBehaviour gameObject : gameObjects)
			gameObject.onDestroy();
	}

	/**
	 * Render method
	 */
	protected void render(Graphics2D g) {
		for (MonoBehaviour gameObject : gameObjects)
			gameObject.render(g);
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	private void openWindow() {
		frame = new JFrame("Basic Game");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
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
}