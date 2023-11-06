import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class PasswordGenerator implements ActionListener, ItemListener {
    String totalDigits = "";
    public static String finalPassword = "";
    public static boolean upper = true;
    public static boolean  lower = true; 
    public static boolean nums = true;
    ArrayList <Integer> elements = new ArrayList<>();
    ArrayList <Integer> initial = new ArrayList<>();
    JFrame myFrame = new JFrame("Password Generator");
    public static JFrame newFrame = new JFrame();
    public static JPanel newPanel = new JPanel();
    
    JPanel panel = new JPanel();
    JLabel welcome = new JLabel("WELCOM TO PASSWORD GENERATOR");
    JTextField input = new  JTextField(10);
    JLabel hint = new JLabel("How many digits?"); 
    JButton confirm = new JButton("Confirm");
    JCheckBox uppercase = new JCheckBox("Uppercase", true);
    JCheckBox lowercase = new JCheckBox("Lowercase", true);
    JCheckBox numbers = new JCheckBox("Numbers", true);
    JButton history = new JButton("Password History");
    JButton clear = new JButton("Clear History");
    public static JTextArea tempResult = new JTextArea(); 
    public static JScrollPane scroll = new JScrollPane(tempResult);
    /***********************************************************************************************************************/
    public PasswordGenerator(){
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 300);
        myFrame.setLocationRelativeTo(null);
        welcome.setFont(new Font("Odibee Sans", Font.PLAIN, 20)); 
        hint.setFont(new Font("Odibee Sans", Font.PLAIN, 15));
        panel.add(welcome);
        panel.add(hint);
        panel.add(input);
        panel.add(confirm);
        panel.add(uppercase);
        panel.add(lowercase);
        panel.add(numbers);
        panel.add(history);
        panel.add(clear);
        uppercase.addItemListener(this);
        lowercase.addItemListener(this);
        numbers.addItemListener(this);
        confirm.addActionListener(this);
        history.addActionListener(this);
        clear.addActionListener(this);
        myFrame.add(panel); 
        myFrame.setVisible(true);
    }
    /***********************************************************************************************************************/
    public void itemStateChanged (ItemEvent event){
        if (event.getSource()==uppercase){
            upper = (event.getStateChange() == ItemEvent.SELECTED);
        }
        if (event.getSource()==lowercase){
            lower = (event.getStateChange() == ItemEvent.SELECTED);
        }
        if (event.getSource()==numbers){
            nums = (event.getStateChange() == ItemEvent.SELECTED);
        }
    }
    /***********************************************************************************************************************/
    public void actionPerformed(ActionEvent event){
        if (event.getSource()==confirm){
            finalPassword = "";
            if (upper && lower && nums){
                resetList();
                upperElements();
                lowerElements();
                numsElements();
                convert();
                update();
            }
            if (upper && lower && !nums){
                resetList();
                upperElements();
                lowerElements();
                convert();
                update();
            }
            if (upper && !lower && !nums){
                resetList();
                upperElements();
                convert();
                update();
            }
            if (!upper && lower && !nums){
                resetList();
                lowerElements();
                convert();
                update();
            }
            if (!upper && lower && nums){
                resetList();
                numsElements();
                lowerElements();
                convert();
                update();
            }
            if (!upper && !lower && nums){
                resetList();
                numsElements();
                convert();
                update();
            }
            if (upper && !lower && nums){
                resetList();
                numsElements();
                upperElements();
                convert();
                update();
            }
            if (!upper && !lower && !nums){
                JOptionPane.showMessageDialog(null,"Please select a password option");
            }
        }
        if (event.getSource() == history){
            showWindow();
        }
        if (event.getSource() == clear){
            tempResult.setText("");
            JOptionPane.showMessageDialog(null,"History has been wiped");
        }
    }
    /***********************************************************************************************************************/
    public char toChar(int c) {
        return (char)c;
    }
    /***********************************************************************************************************************/
    public void convert(){
        totalDigits = input.getText();
        if (totalDigits.equals("")){
            JOptionPane.showMessageDialog(null,"Please enter a valid number");
        }
        int j = Integer.parseInt(totalDigits);
        for (int i = 0; i<j; i++){
            int rand = (int)(Math.random() * elements.size()) + 0;
            char temp = toChar(elements.get(rand)); 
            String random  = String.valueOf(temp);
            finalPassword += random; 
        }
        JOptionPane.showMessageDialog(null,"Password has created");  
    }
    /***********************************************************************************************************************/
    public void upperElements(){
        for (Integer i = 65; i<=90; i++){
            elements.add(i);
        }
    }
    /***********************************************************************************************************************/
    public void lowerElements(){
        for (Integer i = 97; i<=122; i++){
            elements.add(i);
        }
    }
    /***********************************************************************************************************************/
    public void numsElements(){
        for (Integer i = 48; i<=57; i++){
            elements.add(i);
        }
    }
    /***********************************************************************************************************************/
    public void resetList(){
        elements.clear(); 
    }
    /***********************************************************************************************************************/
    public static void update(){
        tempResult.append("Password: " + finalPassword);
        if (upper){
            tempResult.append("\nContains uppercase letters");
        }
        if (lower){
            tempResult.append("\nContains lowercase letters");
        }
        if (nums){
            tempResult.append("\nContains numbers");
        }
        tempResult.append("\n");
        tempResult.append("\n");
    }
    /***********************************************************************************************************************/
    public void showWindow(){
            newFrame.setLocationRelativeTo(null);
            newFrame.setSize(500, 500);
            tempResult.setLineWrap(true);
            tempResult.setEditable(false);
            tempResult.setSize(475,500);
            newPanel.add(tempResult);
            newPanel.add(scroll);
            newFrame.add(newPanel); 
            newFrame.setVisible(true);
    }
}


