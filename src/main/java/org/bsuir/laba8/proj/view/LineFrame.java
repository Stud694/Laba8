package org.bsuir.laba8.proj.view;

import org.bsuir.laba8.proj.controller.LineController;
import org.bsuir.laba8.proj.controller.XmlWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.stream.Collectors;

public class LineFrame extends JFrame {

    private LineController controller;
    private JPanel framePanel;
    private JPanel fieldPanel;
    private JPanel textPanel;
    private JPanel buttonPanel;
    private JPanel limitPanel;
    private JPanel comparePanel;
    private JTextArea textArea;
    private JTextField addTextField;
    private JTextField limitTextField;
    private JTextField firstTextField;
    private JTextField secondTextField;
    private JCheckBox jCheckBox;
    private JButton addButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JButton saveToXmlButton;
    private JButton reverseButton;
    private JButton statButton;
    private JButton subButton;
    private JButton loadButton;
    private JButton compareButton;
    private JButton calcButton;
    private JButton limitButton;
    private JLabel firstLabel;
    private JLabel secondLabel;

    private boolean isLimit = false;

    public LineFrame() {
        super("Лабораторная работа 8");
        controller = new LineController();
        createUIComponents();

        setContentPane(framePanel);
        setSize(1200, 700);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUIComponents() {
        initUIComponents();

        fieldPanel.add(addTextField);
        fieldPanel.add(addButton);
        fieldPanel.add(deleteButton);
        fieldPanel.add(subButton);
        fieldPanel.add(loadButton);
        fieldPanel.add(saveToXmlButton);

        textPanel.add(textArea);

        buttonPanel.add(searchButton);
        buttonPanel.add(reverseButton);
        buttonPanel.add(statButton);
        buttonPanel.add(calcButton);

        comparePanel.add(firstLabel);
        comparePanel.add(firstTextField);
        comparePanel.add(secondLabel);
        comparePanel.add(secondTextField);
        comparePanel.add(compareButton);

        limitPanel.add(jCheckBox);
        limitPanel.add(limitTextField);
        limitPanel.add(limitButton);

        framePanel.add(fieldPanel);
        framePanel.add(textPanel);
        framePanel.add(buttonPanel);
        framePanel.add(comparePanel);
        framePanel.add(limitPanel);

        updateTextArea();
    }

    private void initUIComponents(){
        framePanel = new JPanel();
        fieldPanel = new JPanel();
        textPanel = new JPanel();
        buttonPanel = new JPanel();
        limitPanel = new JPanel();
        comparePanel = new JPanel();

        framePanel.setLayout(new BoxLayout(framePanel, BoxLayout.Y_AXIS));
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.X_AXIS));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        limitPanel.setLayout(new BoxLayout(limitPanel, BoxLayout.X_AXIS));
        comparePanel.setLayout(new BoxLayout(comparePanel, BoxLayout.X_AXIS));

        addButton = new JButton("Добавить строку");
        deleteButton = new JButton("Удалить строку");
        searchButton = new JButton("Поиск одинаковых строк");
        saveToXmlButton = new JButton("Сохранить в xml-файл");
        reverseButton = new JButton("Перевернуть строки");
        statButton = new JButton("Статистика по символам");
        subButton = new JButton("Поиск подстроки в строках");
        loadButton = new JButton("Загрузить текстовый файл");
        compareButton = new JButton("Сравнить две строки");
        calcButton = new JButton("Подсчитать длины строк");
        limitButton = new JButton("Задать лимит количества строк");

        addButton.addActionListener(new AddActionListener());
        deleteButton.addActionListener(new DeleteActionListener());
        searchButton.addActionListener(new SearchRepeatActionListener());
        saveToXmlButton.addActionListener(new SaveToXmlActionListener());
        reverseButton.addActionListener(new ReverseActionListener());
        statButton.addActionListener(new StatisticActionListener());
        subButton.addActionListener(new SubstringActionListener());
        loadButton.addActionListener(new LoadActionListener());
        compareButton.addActionListener(new CompareActionListener());
        calcButton.addActionListener(new CalculatorActionListener());
        limitButton.addActionListener(new LimitActionListener());

        jCheckBox = new JCheckBox("использовать лимит количества строк");
        jCheckBox.addItemListener(new CheckBoxListener());

        textArea = new JTextArea(5, 100);
        textArea.setEditable(false);

        addTextField = new JTextField(300);
        limitTextField = new JTextField(300);
        firstTextField = new JTextField(300);
        secondTextField = new JTextField(300);

        limitTextField.setMaximumSize(new Dimension(200, 30));
        addTextField.setMaximumSize(new Dimension(200, 30));
        firstTextField.setMaximumSize(new Dimension(200, 30));
        secondTextField.setMaximumSize(new Dimension(200, 30));

        firstLabel = new JLabel("Первая строка: ");
        secondLabel = new JLabel("  Вторая строка: ");
    }

    public void updateTextArea(){
        String textList = "";
        if(!isLimit) {
            textList = controller.getArrayList().stream().map(Object::toString)
                    .collect(Collectors.joining("\n"));
        } else {
            textList = controller.getLinkedList().stream().map(Object::toString)
                    .collect(Collectors.joining("\n"));
        }
        textArea.setText(textList);
    }

    public void updateTextField(){
        addTextField.setText("");
    }

    public void printErrorMessage(String errMessage){
        JOptionPane.showMessageDialog(this,
                errMessage, "", JOptionPane.ERROR_MESSAGE);
    }

    public void printWarningMessage(String errMessage){
        JOptionPane.showMessageDialog(this,
                errMessage, "", JOptionPane.WARNING_MESSAGE);
    }

    public void printJnfoMessage(String infoMessage, String title){
        JOptionPane.showMessageDialog(this, infoMessage,
                title, JOptionPane.INFORMATION_MESSAGE);
    }

    //Listeners
    public class CheckBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            isLimit = (e.getStateChange() == 1) ? true: false;
            if(isLimit){
                printWarningMessage("Задайте лимит");
            } else {
                controller.transferToArrayList();
            }
        }
    }

    public class AddActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String line = addTextField.getText();
                if(line == null || line.length() < 1){
                    printWarningMessage("Введите строку");
                    return;
                }

                if(!isLimit){
                    controller.getMyArrayList().add(line);
                } else {
                    controller.getMyLinkedList().add(line);
                }
                updateTextArea();
                updateTextField();

            } catch(Exception ex){
                ex.printStackTrace();
                printErrorMessage("Ошибка приложения");
            }
        }
    }

    public class DeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String line = addTextField.getText();
                if(line == null || line.length() < 1){
                    printWarningMessage("Введите строку");
                    return;
                }

                if(!isLimit){
                    controller.getMyArrayList().remove(line);
                } else {
                    controller.getMyLinkedList().remove(line);
                }
                updateTextArea();
                updateTextField();

            } catch(Exception ex){
                ex.printStackTrace();
                printErrorMessage("Ошибка приложения");
            }
        }
    }

    public class SearchRepeatActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String repeatElements = controller.searchRepeatElements();
                boolean isNotExistsRepeat = (repeatElements == null || repeatElements.length() < 1);
                String message = (isNotExistsRepeat) ? "Не найдено повторов":
                        String.join("\n", "Строки с повтором (количество):", repeatElements);
                printJnfoMessage(message, "Поиск повторов в строках");
            } catch(Exception ex){
                ex.printStackTrace();
                printErrorMessage("Ошибка приложения");
            }
        }
    }

    public class SaveToXmlActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if(controller.getArrayList().size() < 1){
                    printWarningMessage("Список строк пуст");
                    return;
                }

                XmlWriter xmlWriter = new XmlWriter();
                boolean isWrite = xmlWriter.write(controller.getArrayList());
                if(isWrite){
                    String message = String.join(" ", "Данные сохранены в xml-файл: ", xmlWriter.XMLFILEPATH);
                    printJnfoMessage(message, "Сохранение в xml");
                } else {
                    printErrorMessage("Ошибка при сохранении xml");
                }
            } catch(Exception ex){
                ex.printStackTrace();
                printErrorMessage("Ошибка приложения");
            }
        }
    }

    public class ReverseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                controller.reverseList();
                updateTextArea();
            } catch(Exception ex){
                ex.printStackTrace();
                printErrorMessage("Ошибка приложения");
            }
        }
    }

    public class StatisticActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String result = controller.charStatistics();
                printJnfoMessage(String.join("\n", "Статистика символов: ", result),
                        "Статистика по символам");
            } catch(Exception ex){
                ex.printStackTrace();
                printErrorMessage("Ошибка приложения");
            }
        }
    }

    public class SubstringActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String subLine = addTextField.getText();
                if(subLine == null || subLine.length() < 1) {
                    printErrorMessage("Введите подстроку");
                    return;
                }

                String resultList = controller.findSubstring(subLine);
                boolean isNotExistsResult = (resultList == null || resultList.length() < 1);
                String message = (isNotExistsResult) ? "Подстрока не найдена":
                        String.join("\n", "Подстрока найдена в строках:", resultList);
                printJnfoMessage(message, "Поиск подстроки");
                updateTextField();
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public class LoadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT files", "txt");
                fileChooser.setFileFilter(filter);
                int ret = fileChooser.showDialog(null, "Открыть txt-файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    controller.loadFile(file);
                    updateTextArea();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public class CalculatorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String resultMsg = controller.countingLength();
                printJnfoMessage(String.join("\n", "Длины строк: ", resultMsg),
                        "Подсчёт длин строк");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class CompareActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                new CompareFrame(controller);
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public class LimitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isLimit){
                String limit = limitTextField.getText();
                if(limit == null || limit.length() < 1){
                    printWarningMessage("Введите лимит");
                    return;
                }

                controller.setLimitLinkedList(Integer.parseInt(limit));
                controller.transferToLinkedList();
                updateTextArea();
            } else {
                printWarningMessage("Не задан флаг использования лимита строк");
            }
            limitTextField.setText("");
        }
    }

}
