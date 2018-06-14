import javax.swing.*;

/**
 * Main class
 * @author Jan Schelhaas, Pascal Polchow, Larissa Wagnerberger
 * @version 2018.06.13
 */
public class Calculator {
    private SetInterface gui;

	/**
	 * Create a new calculator and show it.
	 */

	public static void main (String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException e) {
			e.printStackTrace();
		}
		new Calculator();
	}
	private Calculator() {
        Engine engine = new Engine();
		gui = new SetInterface(engine);
	}

	/**
	 * In case the window was closed, show it again.
	 */
	public void show() {
		gui.setVisible(true);
	}
}
