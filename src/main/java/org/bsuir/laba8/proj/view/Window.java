package org.bsuir.laba8.proj.view;

import org.bsuir.laba8.proj.controller.LineController;
import org.bsuir.laba8.proj.controller.XmlWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import static java.awt.Component.LEFT_ALIGNMENT;

public class Window {

    final JTextArea textArea = new JTextArea(5, 100);

    final JTextField addField = new JTextField(300);
    final JTextField subField = new JTextField(300);
    {
        addField.setMaximumSize(new Dimension(200, 30));
        subField.setMaximumSize(new Dimension(200, 30));
    }

    final JButton addButton = new JButton("Добавить строку");
    final JButton deleteButton = new JButton("Удалить строку");
    final JButton searchButton = new JButton("Поиск одинаковых элементов");
    final JButton saveToXmlButton = new JButton("Сохранить в xml-файл");
    final JButton reverseButton = new JButton("Перевернуть строки");
    final JButton statButton = new JButton("Статистика по символам");
    final JButton subButton = new JButton("Поиск подстроки в строках");
    final JButton loadButton = new JButton("Загрузить текстовый файл");
    final JButton compareButton = new JButton("Сравнить две строки");
    final JButton calcButton = new JButton("Подсчитать длины строк");

    {
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
    }

    JComboBox<String> jComboBox1 = new JComboBox<>();
    JComboBox<String> jComboBox2 = new JComboBox<>();
    {
//        jComboBox.setBounds(80, 50, 140, 20);
//        jComboBox.setBounds(80, 50, 140, 20);
    }

    private LineController controller;

    public Window() {
        controller = new LineController();
        initComponents();
        updateTextArea();
    }

    private JFrame windowForm;

    private void initComponents() {
        windowForm = new JFrame("Лабораторная работа 8");
        windowForm.setSize(1200, 700);
        windowForm.setVisible(true);
        windowForm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel windowPanel = new JPanel();
        windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.X_AXIS));

        //textArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);

        //groupButton
        JPanel groupButton = new JPanel();
        groupButton.setLayout(new BoxLayout(groupButton, BoxLayout.Y_AXIS));

        //1.добавить/удалить
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        panel1.add(addField);
        panel1.add(addButton);
        panel1.add(deleteButton);

        //2.поиск одинаковых элементов с подсчётом совпадений
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.add(searchButton);

        //3.выгрузка в xml
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
        panel3.add(saveToXmlButton);

        //4.реверс всех строк
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
        panel4.add(reverseButton);

        //5.статистика символов
        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        panel5.add(statButton);

        //6.поиск подстроки в строках коллекции
        JPanel panel6 = new JPanel();
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.X_AXIS));
        panel6.add(subField);
        panel6.add(subButton);

        //7.загрузить текстовый файл - проинициализировать - отобразить
        JPanel panel7 = new JPanel();
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.X_AXIS));
        panel7.add(loadButton);

        //8.compareInnerObjects
        JPanel panel8 = new JPanel();
        panel8.setLayout(new BoxLayout(panel8, BoxLayout.X_AXIS));
        panel8.add(compareButton);

        //9.подсчитать длины строк коллекции - вывести по порядку
        JPanel panel9 = new JPanel();
        panel9.setLayout(new BoxLayout(panel9, BoxLayout.X_AXIS));
        panel9.add(calcButton);

        //10.задать размер - при переполнении - удалять первый
        JPanel panel10 = new JPanel();
        panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));
        //panel10.add(calcButton);

        //x
        groupButton.add(panel1);
        groupButton.add(panel2);
        groupButton.add(panel3);
        groupButton.add(panel4);
        groupButton.add(panel5);
        groupButton.add(panel6);
        groupButton.add(panel7);
        groupButton.add(panel8);
        groupButton.add(panel9);
        //groupButton.add(panel10);

        windowPanel.add(textArea);
        windowPanel.add(groupButton);

        windowForm.getContentPane().add(windowPanel, BorderLayout.CENTER);
    }

    public void updateTextArea(){
        String list = controller.getListToPrint();
        textArea.setText(list);
    }

    public void showErrorPane(String errMessage){
        JOptionPane.showMessageDialog(windowForm,
                errMessage, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public void showInfoPane(String infoMessage, String title){
        JOptionPane.showMessageDialog(windowForm, infoMessage,
                title, JOptionPane.INFORMATION_MESSAGE);
    }

    //Listeners
    public class AddActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String line = addField.getText();
            if(line == null || line.length() < 1){
                showErrorPane("Строка пуста");
            }
            //controller.add(line);
            updateTextArea();
        }
    }

    public class DeleteActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String line = addField.getText();
            if(line == null || line.length() < 1){
                showErrorPane("Строка пуста");
            }
            //controller.delete(line);
            updateTextArea();
        }
    }

    public class SearchRepeatActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String repeatElements = controller.searchRepeatElements();
            if(repeatElements == null || repeatElements.length() < 1){
                showErrorPane("Список строк пуст");
            } else {
                showInfoPane(repeatElements, "Строка - число повторений");
            }
        }
    }

    public class SaveToXmlActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> arrayList = controller.getArrayList();
            if(arrayList.size() < 1){
                showErrorPane("Список строк пуст");
            }
            XmlWriter xmlWriter = new XmlWriter();
            boolean isWrite = xmlWriter.write(arrayList);
            if(isWrite){
                showInfoPane("Данные выгружены в xml-файл: "+xmlWriter.XMLFILEPATH, "Выгрузка в xml");
            } else {
                showErrorPane("Ошибка при выгрузке xml");
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
            }
        }
    }


    public class StatisticActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String result = controller.charStatistics();
                showInfoPane(result, "Статистика по символам");
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public class SubstringActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String subLine = subField.getText();
                if(subLine == null || subLine.length() < 1) {
                    showErrorPane("Введите подстроку");
                } else {
//                    boolean isFind = controller.findSubstring(subLine);
//                    String message = (isFind) ? "подстрока найдена" : "подстрока не найдена";
//                    showInfoPane(message, "Поиск подстроки");
                }
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

    public class CompareActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int firstIndex = 1;
                int secondIndex = 2;

                String message = "";
                int result = controller.compare(firstIndex, secondIndex);
                if(result == 0){
                    message = "Строки равны";
                } else if(result < 0){
                    message = "Первая строка меньше второй";
                } else {
                    message = "Первая строка больше второй";
                }

                showInfoPane(message, "Сравнение строк");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public class CalculatorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String resultMsg = controller.countingLength();
                showInfoPane(resultMsg, "Подсчёт длин строк");
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
