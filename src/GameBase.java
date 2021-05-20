import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

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

	private float deltaTime;

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
	 * Open window and Awake
	 */
	protected void OpenWindow() {
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

		awake();
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
	protected abstract void awake();

	/**
	 * Start method like Unity
	 */
	protected abstract void start();

	/**
	 * Update method like Unity
	 */
	protected abstract void update();

	/**
	 * OnDestroy method like Unity
	 */
	protected abstract void onDestroy();

	/**
	 * Render method
	 */
	protected abstract void render(Graphics2D g);

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH_OF_WINDOW, HEIGHT_OF_WINDOW);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}
}