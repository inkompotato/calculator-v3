import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * UserInterface
 * @author Jan Schelhaas, Pascal Polchow, Larissa Wagnerberger
 * @version 2018.06.13
 */
public class UserInterface implements ActionListener {
	Engine calc;

	JFrame frame;
	JTextField display;
	private JLabel status;
	private JLabel error;

	/**
	 * Create a user interface.
	 * 
	 * @param engine
	 *            The calculator engine.
	 */
	UserInterface(Engine engine) {
		calc = engine;
		boolean showingAuthor = true;
		makeFrame();
		frame.setVisible(true);
	}

	/**
	 * Set the visibility of the interface.
	 * 
	 * @param visible
	 *            true if the interface is to be made visible, false otherwise.
	 */
	void setVisible(boolean visible) {
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame() {
		frame = new JFrame("Calculator");

		JPanel contentPane = (JPanel) frame.getContentPane();

		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		display = new JTextField();
		contentPane.add(display, BorderLayout.NORTH);

		JPanel innerPane = new JPanel(new BorderLayout());
		JPanel buttonPanel = new JPanel(new GridLayout(5, 4));

		addButton(buttonPanel, "(");
		addButton(buttonPanel, ")");
		addButton(buttonPanel, "^");
		addButton(buttonPanel, "CE");


		addButton(buttonPanel, "7");
		addButton(buttonPanel, "8");
		addButton(buttonPanel, "9");
		addButton(buttonPanel, "+");

		addButton(buttonPanel, "4");
		addButton(buttonPanel, "5");
		addButton(buttonPanel, "6");
		addButton(buttonPanel, "-");

		addButton(buttonPanel, "1");
		addButton(buttonPanel, "2");
		addButton(buttonPanel, "3");
		addButton(buttonPanel, "*");

		addButton(buttonPanel, "+/-");
		addButton(buttonPanel, "0");
		addButton(buttonPanel, "=");
		addButton(buttonPanel, "/");

		innerPane.add(buttonPanel, BorderLayout.CENTER);

		JPanel statuspanel = new JPanel (new BorderLayout());
		error = new JLabel(calc.getError());
		statuspanel.add(error, BorderLayout.WEST);

		status = new JLabel(calc.getStatus());
		statuspanel.add(status, BorderLayout.EAST);

		innerPane.add(statuspanel, BorderLayout.SOUTH);

		contentPane.add(innerPane, BorderLayout.CENTER);

		frame.pack();
	}

	/**
	 * Add a button to the button panel.
	 * 
	 * @param panel
	 *            The panel to receive the button.
	 * @param buttonText
	 *            The text for the button.
	 */
	private void addButton(Container panel, String buttonText) {
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and handle it.
	 * 
	 * @param event
	 *            The event that has occured.
	 */
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
				int number = Integer.parseInt(command);
				calc.numberPressed(number);
				break;
			case "+":
				calc.op(command);
				break;
			case "-":
				calc.op(command);
				break;
			case "=":
				calc.equals();
				break;
			case "CE":
				calc.clear();
				break;
			case "*":
				calc.op(command);
				break;
			case "/":
				calc.op(command);
				break;
			case "^":
				calc.op(command);
				break;
			case "(":
				calc.op(command);
				break;
			case ")":
				calc.op(command);
				break;
			case "+/-":
				calc.negate();
				break;
		}
		// else unknown command.

		redisplay();
	}

	/**
	 * Update the interface display to show the current value of the calculator.
	 */
	protected void redisplay() {
		showInfo();
		display.setText("" + calc.getDisplayString());
	}

	/**
	 * Toggle the info display in the calculator's status area between the author
	 * and version information.
	 */
	void showInfo() {
		status.setText(calc.getStatus());
		error.setText(calc.getError());
	}
}
