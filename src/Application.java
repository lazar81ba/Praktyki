
import appclases.*;
import appclases.Action;
import logcreator.Log;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


import javax.swing.*;



public class Application extends JFrame implements ItemListener{

    private final Log log;
    private final Action action;
    private JTextField parameterValue;
    private JLabel actionLabel;
    private JLabel communicationLabel;
    private ButtonGroup group;


    //options for RadioButtons
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
        private final String action;
        private final boolean parameter;

    }

    private Application() {
        log = new Log();
        action = new Action();
        initUI();
    }

    private void initUI() {
        createStartLayout();
    }



    private void createStartLayout() {
        // labels, fields
        JLabel email = new JLabel("Email");
        email.setFont(new Font("Serif", Font.PLAIN, 16));
        email.setForeground(new Color(50, 50, 25));

        JLabel token = new JLabel("Token");
        token.setFont(new Font("Serif", Font.PLAIN, 16));
        token.setForeground(new Color(50, 50, 25));

        JTextField emailText = new JTextField(25);
        JTextField tokenText = new JTextField(25);
        JButton submitButton = new JButton("Submit");

        JLabel errorLabel = new JLabel("Cannot make connection");
        errorLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        errorLabel.setForeground(new Color(250, 20, 10));
        errorLabel.setVisible(false);

        //setting email and token for convenience
        emailText.setText("lazar81ba@gmail.com");
        tokenText.setText("61ADBBB5AEBBEAF640AEBEBFD0CB751F");


        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());

        //making new Panel
        JPanel cp=new JPanel();
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
                    .addComponent(errorLabel)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                    .addComponent(email)
                    .addComponent(emailText)
                    .addComponent(token)
                    .addComponent(tokenText)
                    .addComponent(submitButton)
                    .addComponent(errorLabel)
        );


        //listener for email text field if it changes, also checking email format
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

        //button listener, setting account in client,removing panel
        submitButton.addActionListener(event -> {
            Client.setAccount(new Account(emailText.getText(),tokenText.getText()));

            try {
                if(Client.checkConnection()){
                    remove(cp);
                createActionLayout();
                }else errorLabel.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        revalidate();
        repaint();
        pack();
        setTitle("JComboBox");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }


    private void createActionLayout() throws IOException {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        JButton submitButton = new JButton("Submit");

        parameterValue = new JTextField();
        parameterValue.setEditable(false);

        JLabel errorLabel = new JLabel("None parameter selected");
        errorLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        errorLabel.setForeground(new Color(250, 20, 10));
        errorLabel.setVisible(false);

        JLabel parameterLabel = new JLabel("Parameter: ");
        parameterLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        parameterLabel.setForeground(new Color(50, 50, 25));

        actionLabel = new JLabel("<html><br>Selected action: "+action.getOption()+"<br>Parameter: "+action.getParameter()+"<br></html>");
        actionLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        actionLabel.setForeground(new Color(50, 50, 25));

        communicationLabel = new JLabel(formatToJlabel(Client.describe()),SwingConstants.CENTER);
        communicationLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        communicationLabel.setForeground(new Color(50, 50, 25));

        group = new ButtonGroup();

        //creating layoutGroups for my convenience
        GroupLayout.ParallelGroup leftPanelParallel = gl.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup leftPanelSequential = gl.createSequentialGroup();

        GroupLayout.SequentialGroup parameterSequential=gl.createSequentialGroup()
                                            .addComponent(parameterLabel)
                                            .addComponent(parameterValue,10,50,50);

        GroupLayout.ParallelGroup parameterParallel = gl.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(parameterLabel)
                                            .addComponent(parameterValue,25,25,25);

        //add every option to group as JRadioButton
        for (option var:option.values()
                ) {
            JRadioButton button = new JRadioButton(var.action);
            button.addItemListener(this);
            group.add(button);
            leftPanelParallel.addComponent(button,120,250,250);
            leftPanelSequential.addComponent(button,25,25,25);
        }

        leftPanelParallel.addGroup(parameterSequential);
        leftPanelSequential.addGroup(parameterParallel);

        leftPanelParallel.addComponent(actionLabel);
        leftPanelSequential.addComponent(actionLabel);

        leftPanelParallel.addComponent(submitButton);
        leftPanelSequential.addComponent(submitButton);

        leftPanelParallel.addComponent(errorLabel);
        leftPanelSequential.addComponent(errorLabel);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addGroup(leftPanelParallel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(communicationLabel,300,600,600)))

        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(leftPanelSequential)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(communicationLabel)))
        );

        //listener for parameterValue, check correctness and setting action parameter
        parameterValue.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                String content = parameterValue.getText();
                if(content.matches("[\\d]{1,3}")){
                    if(Integer.parseInt(content) <= 0 ) content="0";
                    else if(Integer.parseInt(content)>=200)content = "200";
                        parameterValue.setText(content);
                        action.setParameter(content);
                        actionLabel.setText("<html><br>Selected action: "+action.getOption()+"<br>Parameter: "+action.getParameter()+"<br></html>");
                }
                else if(!content.equals("")) parameterValue.setText(content.substring(0,content.length()-1));
                else { // sprawdz content czy = "" i sprawdz czy wybrana opcja ma parameter
                    action.setParameter("none");
                    actionLabel.setText("<html><br>Selected action: "+action.getOption()+"<br>Parameter: "+action.getParameter()+"<br></html>");
                }

            }
        });


        //submitButton listener, proceed request

        submitButton.addActionListener(e -> {
            if(!action.getOption().equals("")&&!action.getParameter().equals("none")) {
                try {
                    Client.request(action);
                    String message = Client.describe();
                    log.writeLog(message,action);
                    communicationLabel.setText(formatToJlabel(message));
                    group.clearSelection();
                    parameterValue.setText("");
                    action.resetAction();
                    actionLabel.setText("<html><br>Selected action: " + action.getOption() + "<br>Parameter: " + action.getParameter() + "<br></html>");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }else errorLabel.setVisible(true);
        });


        revalidate();
        repaint();
        pack();
        setTitle("JComboBox");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    //action performed after RadioButton check
    @Override
    public void itemStateChanged(ItemEvent e) {
        int sel = e.getStateChange();
        if (sel == ItemEvent.SELECTED) {
            JRadioButton button = (JRadioButton) e.getSource();
            String selectedOption = button.getText();
            action.setOption(selectedOption);
            for (option var:option.values()  //checking all possible options if one matches selected option
                 ) {
                if(var.action.equals(selectedOption)) {
                    parameterValue.setEditable(var.parameter);// setting parameterValue editable or not editable
                    actionLabel.setText("<html><br>Selected action: "+action.getOption()+"<br>Parameter: "+action.getParameter()+"<br></html>");
                    break;
                }
            }

        }
    }

    //formating String into Jlabel format
    //replacing new lines
    private String formatToJlabel(String message){
        message = message.replace(System.lineSeparator(),"<br>");
        String returnString = "<html>";
        returnString  = returnString.concat(message+"</h");
        return returnString;
    }

    //main method
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });
    }
}