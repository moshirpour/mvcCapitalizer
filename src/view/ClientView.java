package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;

public class ClientView extends JFrame {

    private JTextField userInput = new JTextField(10);
   // JLabel addLabel = new JLabel("+");

 // JTextField secondNumber = new JTextField(10);
    private JButton capitlizeButton = new JButton("Capitalize");
    private JButton allLowerCaseButton = new JButton("All Lower Case");

    private JTextField resultedString = new JTextField(10);

    public ClientView (){
        JPanel calcPanel = new JPanel();
        this.setSize(300, 300);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        calcPanel.add(userInput);
        calcPanel.add(capitlizeButton);
        calcPanel.add(allLowerCaseButton);
        calcPanel.add(resultedString);

        this.add(calcPanel);
        
        setVisible (true);
    }

    public  String getUserInput() {
        return userInput.getText();
    }
   
    public void setResultedString (String result){
        resultedString.setText(result);
    }

    public void addCapListener (ActionListener listenForCapButton){
    	capitlizeButton.addActionListener(listenForCapButton);
    }
    public void addCloseListener (WindowAdapter windowAdapter) {
    	 addWindowListener(windowAdapter);
    }
    public void displayErrorMessage (String errorMessage){
        JOptionPane.showMessageDialog(this,errorMessage);
    }

	

}
