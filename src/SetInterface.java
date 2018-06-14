import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SetInterface extends HexInterface implements ActionListener {


    private JTextField resultarea;
    private JTextField set1;
    private JTextField set2;
    private JLabel statusl;

    SetInterface(Engine engine) {
        super(engine);
        assembleset();
        frame.setVisible(true);
    }

    private void assembleset() {
        JPanel contentPane = (JPanel) frame.getContentPane();

        JPanel setpanel = new JPanel(new BorderLayout());

        JPanel setbuttons = new JPanel(new GridLayout(2,2));
        JButton intb = new JButton("INT");
        intb.addActionListener((ActionEvent e) -> {
            resultarea.setText(calc.setIntersect(getSet1(), getSet2()));
            getSize();
        });
        setbuttons.add(intb);
        JButton unib = new JButton("UNI");
        unib.addActionListener((ActionEvent e) -> {
            resultarea.setText(calc.setUnion(getSet1(), getSet2()));
            getSize();
        });
        setbuttons.add(unib);
        JButton subb = new JButton("SUB");
        subb.addActionListener((ActionEvent e) -> {
            resultarea.setText(calc.setSubtract(getSet1(), getSet2()));
            getSize();
        });
        setbuttons.add(subb);
        JButton clrb = new JButton("CLR");
        clrb.addActionListener((ActionEvent e) -> {
            resultarea.setText("");
            set1.setText("");
            set2.setText("");
            getSize();
        });
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
        appendcalc(add1, set1);
        addbuttons.add(add1);
        JButton add2 = new JButton("+");
        appendcalc(add2, set2);
        addbuttons.add(add2);

        setpanel.add(addbuttons, BorderLayout.WEST);

        statusl = new JLabel("SETSIZE: 0");
        setpanel.add(statusl, BorderLayout.SOUTH);

        contentPane.add(setpanel, BorderLayout.SOUTH);

        frame.pack();

    }

    private void appendcalc(JButton add2, JTextField set2) {
        add2.addActionListener((ActionEvent e) ->{
            String s = calc.addToSet();
            String str = set2.getText();
            if(str.isEmpty()){
                set2.setText(s);
            }
            else{
                set2.setText(str+","+s);
            }
        });
    }

    private void getSize(){
        int i = calc.getSize(resultarea.getText());
        statusl.setText("SETSIZE: "+String.valueOf(i));
    }

    private String getSet1(){
        return set1.getText();
    }

    private String getSet2() {
        return set2.getText();
    }
}
