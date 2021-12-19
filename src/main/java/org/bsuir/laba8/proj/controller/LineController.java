package org.bsuir.laba8.proj.controller;

import org.bsuir.laba8.proj.model.LineArrayList;
import org.bsuir.laba8.proj.model.LineLinkedList;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LineController {

    private LineArrayList arrayList;
    private LineLinkedList linkedList;

    public LineController() {
        arrayList = new LineArrayList();
        linkedList = new LineLinkedList();
    }

    //поиск повторов строк и их количества
    public String searchRepeatElements(){
        Map<String, Integer> map = new HashMap<>();   //элемент - число повторов
        ArrayList<String> resultList = new ArrayList<>();

        for(int i = 0; i < arrayList.size(); i++){
            String line = arrayList.get(i);
            int count = (map.containsKey(line)) ? map.get(line)+1 : 1;
            map.put(line, count);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String line = entry.getKey();
            int count = entry.getValue();
            if(count > 1){
                resultList.add(String.join(" ", line, String.valueOf(count)));
            }
        }

        return resultList.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    //реверс строк
    public void reverseList() {
        for(int i = 0; i < arrayList.getArrayList().size(); i++){
            String line = arrayList.getArrayList().get(i);
            String reverseLine = new StringBuilder(line).reverse().toString();
            arrayList.getArrayList().set(i, reverseLine);
        }
    }

    //статистика по символам
    public String charStatistics(){
        SortedMap<Character, Integer> map = new TreeMap<>();
        for(int i = 0; i < arrayList.getArrayList().size(); i++){
            String line = arrayList.getArrayList().get(i);
            char[] array = line.toCharArray();
            for(int j = 0; j < array.length; j++){
                char ch = array[j];
                int count = (map.containsKey(ch)) ? map.get(ch)+1: 1;
                map.put(ch, count);
            }
        }

        return map.keySet().stream().map(key -> "'" + key + "'   " + map.get(key))
                .collect(Collectors.joining("\n"));
    }

    //поиск подстроки в строках
    public String findSubstring(String line) {
        List<String> resultList = new ArrayList<String>();
        for(int i = 0; i < arrayList.getArrayList().size(); i++) {
            String str = arrayList.getArrayList().get(i);
            if(str.indexOf(line) >= 0){
                resultList.add(str);
            }
        }
        return resultList.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    //загрузка текстового файла
    public void loadFile(File file){
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            String line = reader.readLine();
            if(line == null || line.length() < 1){
                return;
            }
            arrayList.clear();
            arrayList.add(line);

            while ((line = reader.readLine()) != null) {
                arrayList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int compare(int firstIndex, int secondIndex){
        return arrayList.compareInnerObjects(firstIndex, secondIndex);
    }

    public String countingLength(){
        ArrayList<String> resultList = new ArrayList<>();
        for(int i = 0; i < arrayList.getArrayList().size(); i++) {
            String line = arrayList.getArrayList().get(i);
            String result = line + "  " + line.length();
            resultList.add(result);
        }
        return resultList.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public String getListToPrint(){
        return arrayList.getArrayList().stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public void transferToLinkedList(){
        linkedList.setLinkedList(arrayList);
    }

    public void setLimitLinkedList(int limit){
        linkedList.setCapacity(limit);
    }

    public void transferToArrayList(){
        arrayList.setArrayList(linkedList.getLinkedList());
    }

    public LineArrayList getMyArrayList() {
        return arrayList;
    }

    public LineLinkedList getMyLinkedList() {
        return linkedList;
    }

    public ArrayList<String> getArrayList() {
        return arrayList.getArrayList();
    }

    public LinkedList<String> getLinkedList() {
        return linkedList.getLinkedList();
    }

}
