package org.bsuir.laba8.proj.view;

import org.bsuir.laba8.proj.controller.LineController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompareFrame extends JFrame {

    private LineController controller;
    private JPanel comparePanel;
    private JComboBox<String> firstComboBox;
    private JComboBox<String> secondComboBox;
    private JLabel firstLabel;
    private JLabel secondLabel;
    private JButton compareButton;

    public CompareFrame(LineController controller){
        super("Сравнение строк");
        this.controller = controller;
        createUIComponents();

        setContentPane(comparePanel);
        setSize(700, 300);
        setLocation(430, 100);
        setVisible(true);
    }

    private void createUIComponents() {
        comparePanel = new JPanel();
        comparePanel.setLayout(new BoxLayout(comparePanel, BoxLayout.X_AXIS));

        String[] array = controller.getArrayList().stream().toArray(String[]::new);
        firstComboBox = new JComboBox<String>(array);
        secondComboBox = new JComboBox<String>(array);

        firstLabel = new JLabel("первая строка: ");
        secondLabel = new JLabel("вторая строка: ");

        compareButton = new JButton("Сравнить");
        compareButton.addActionListener(new CompareActionListener());

        comparePanel.add(firstLabel);
        comparePanel.add(firstComboBox);
        comparePanel.add(secondLabel);
        comparePanel.add(secondComboBox);
        comparePanel.add(compareButton);
    }

    public void printJnfoMessage(String infoMessage, String title){
        JOptionPane.showMessageDialog(this, infoMessage,
                title, JOptionPane.INFORMATION_MESSAGE);
    }

    public class CompareActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int firstIndex = firstComboBox.getSelectedIndex();
                int secondIndex = secondComboBox.getSelectedIndex();
                int result = controller.compare(firstIndex, secondIndex);

                String message = "Строки равны";
                if(result < 0){
                    message = "Первая строка меньше второй";
                } else {
                    message = "Первая строка больше второй";
                }

                printJnfoMessage(message, "Сравнение строк");
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
