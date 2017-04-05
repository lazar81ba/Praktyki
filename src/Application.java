
import appclases.*;

import java.awt.*;
import java.awt.event.*;


import javax.swing.*;



public class Application extends JFrame {

    private Account account;
    private JComboBox<String> box;

    private enum option{
        IMPORTFOOD ("Import Food",true),
        PRODUCE ("Produce",false),
        PROPAGANDA ("Propaganda",true),
        CLEAN ("Clean",true),
        BUILDARCOLOGY ("Build Arcology",true),
        EXPOPCAP("Expand Population Capacity",true),
        EXFOODCAP("Expand Food Capacity",true),
        READY("We are ready",true),
        RESTART("Restart",false);

        option(String action,boolean parameter){
            this.action=action;
            this.parameter=parameter;
        }
        private String action;
        private boolean parameter;
        private String action(){return action;}
        private boolean isParameter(){return parameter;}
    }

    public Application() {
        account = new Account();
        initUI();
    }

    private void initUI() {
        createStartLayout();
    }



    private void createStartLayout() {
        setTitle("JComboBox");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel email = new JLabel("Email");
        email.setFont(new Font("Serif", Font.PLAIN, 16));
        email.setForeground(new Color(50, 50, 25));

        JLabel token = new JLabel("Token");
        token.setFont(new Font("Serif", Font.PLAIN, 16));
        token.setForeground(new Color(50, 50, 25));

        JTextField emailText = new JTextField(25);
        JTextField tokenText = new JTextField(25);
        JButton submitButton = new JButton("Submit");

        emailText.setText("lazar81ba@gmail.com");
        tokenText.setText("61ADBBB5AEBBEAF640AEBEBFD0CB751F");


        Container pane = getContentPane();

        JPanel cp=new JPanel();
        pane.setLayout(new GridBagLayout());
        add(cp);

        GroupLayout gl = new GroupLayout(cp);
        cp.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(
                gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(email)
                    .addComponent(emailText)
                    .addComponent(token)
                    .addComponent(tokenText)
                    .addComponent(submitButton)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                    .addComponent(email)
                    .addComponent(emailText)
                    .addComponent(token)
                    .addComponent(tokenText)
                    .addComponent(submitButton)
        );



        emailText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                String content = emailText.getText();
                if (content.matches("\\w++@\\w++\\.\\w++")) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                account.setEmail(emailText.getText());
                account.setToken(tokenText.getText());
                remove(cp);
                createActionLayout();
            }
        });

        revalidate();
        repaint();
        pack();
        setTitle("JComboBox");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private void createActionLayout(){

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        JButton test1 = new JButton("test1");
        JButton test2 = new JButton("test2");
        JTextArea area1 = new JTextArea();
        JTextArea area2 = new JTextArea();
        JComboBox<option> box = new JComboBox<>(option.values());

        GroupLayout.SequentialGroup seq=gl.createSequentialGroup()
                                            .addComponent(box,120,200,200)
                                            .addComponent(test1);

        GroupLayout.ParallelGroup paral = gl.createParallelGroup()
                                            .addComponent(box,25,25,25)
                                            .addComponent(test1);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(seq))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(area2,100, 200,300)
                                .addComponent(test2)))
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(gl.createSequentialGroup()
                                .addGroup(paral))
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(area2,100, 200,300)
                                .addComponent(test2)))
        );


        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Object item = e.getItem();
                    // do something with object
                    if(((option) item).parameter){
                    seq.addComponent(area1,50,50,50);
                    paral.addComponent(area1,25,25,25);
                    }
                }
            }
        });

        revalidate();
        repaint();
        pack();
        setTitle("JComboBox");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}