
import appclases.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.*;



public class Application extends JFrame {

    private Account account;

    public Application() {
        account = new Account();
        initUI();
    }

    private void initUI() {


        createStartLayout();

        setTitle("JComboBox");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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
                gl.createParallelGroup()
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
                revalidate();
                repaint();
                pack();
                setTitle("JComboBox");
                setSize(900, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
            }
        });

        pack();

    }

    private void createActionLayout(){

        JButton button = new JButton("lol");

        Container pane = getContentPane();

        JPanel cp=new JPanel();
        pane.setLayout(new GridBagLayout());
        add(cp);

        GroupLayout gl = new GroupLayout(cp);
        cp.setLayout(gl);

        gl.setAutoCreateGaps(true);
        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(
                gl.createParallelGroup()
                        .addComponent(button)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addComponent(button)
        );

        pack();

    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}