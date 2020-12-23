package ru.geekbrains.java_level_1.chat_window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {

    private JTextField inputField;
    private JButton send;
    private JPanel jPanel;
    private JTextArea messagesField;
    private String enteredMessage;

    public ChatWindow() {
        setTitle("Chat Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(300,300,500,500);
        setLayout(new BorderLayout());
        jPanel = new JPanel(new GridLayout(1,2));
        send = new JButton("Send");
        inputField = new JTextField(5);
        send.setBounds(100,100,100,30);
        jPanel.add(inputField);
        jPanel.add(send);
        add(jPanel, BorderLayout.SOUTH);
        messagesField = new JTextArea();
        messagesField.setEditable(false);
        add(messagesField, BorderLayout.CENTER);
        setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enteredMessage = inputField.getText();
                messagesField.append(enteredMessage + "\n");
                inputField.setText("");
            }
        });
    }




}
