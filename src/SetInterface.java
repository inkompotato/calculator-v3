import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetInterface extends HexInterface implements ActionListener {


    private JTextField resultarea;
    private JTextField set1;
    private JTextField set2;
    private String resultString;

    public SetInterface(Engine engine) {
        super(engine);
        assembleset();

        frame.setVisible(true);
    }

    private void assembleset() {
        JPanel contentPane = (JPanel) frame.getContentPane();

        JPanel setpanel = new JPanel(new BorderLayout());

        JPanel setbuttons = new JPanel(new GridLayout(2,2));
        JButton intb = new JButton("INT");
        intb.addActionListener((ActionEvent e) -> calc.setIntersect());
        setbuttons.add(intb);
        JButton unib = new JButton("UNI");
        unib.addActionListener((ActionEvent e) -> calc.setUnion());
        setbuttons.add(unib);
        JButton subb = new JButton("SUB");
        subb.addActionListener((ActionEvent e) -> calc.setSubtract());
        setbuttons.add(subb);
        JButton clrb = new JButton("CLR");
        clrb.addActionListener((ActionEvent e) -> calc.setClear());
        setbuttons.add(clrb);

        setpanel.add(setbuttons, BorderLayout.EAST);

        resultarea = new JTextField();
        resultarea.setEditable(false);
        setpanel.add(resultarea, BorderLayout.NORTH);

        JPanel setfields = new JPanel(new BorderLayout());
        set1 = new JTextField();
        set2 = new JTextField();

        setfields.add(set1, BorderLayout.NORTH);
        setfields.add(set2, BorderLayout.SOUTH);

        setpanel.add(setfields, BorderLayout.CENTER);

        JPanel addbuttons = new JPanel(new GridLayout(2,1));
        JButton add1 = new JButton("+");
        add1.addActionListener((ActionEvent e) -> calc.addToSet(1));
        addbuttons.add(add1);
        JButton add2 = new JButton("+");
        add2.addActionListener((ActionEvent e) -> calc.addToSet(2));
        addbuttons.add(add2);

        setpanel.add(addbuttons, BorderLayout.WEST);

        contentPane.add(setpanel, BorderLayout.SOUTH);

        frame.pack();

    }

    void setResultString(String str){
        resultString = str;
    }

    String getSet1(){
        return set1.getText();
    }

    String getSet2() {
        return set2.getText();
    }
}
