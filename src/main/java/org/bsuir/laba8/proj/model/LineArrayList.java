package org.bsuir.laba8.proj.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class LineArrayList extends ArrayList {

    private ArrayList<String> arrayList;

    public LineArrayList() {
        arrayList = new ArrayList<>();
    }

    public void add(String line) {
        arrayList.add(line);
    }

    public void remove(String line) {
        arrayList.remove(line);
    }

    public String get(int index) {
        return arrayList.get(index);
    }

    public int size() {
        return arrayList.size();
    }

    public void clear() {
        arrayList.clear();
    }

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(LinkedList<String> linkedList) {
        arrayList.clear();
        arrayList.addAll(linkedList);
    }

    public int compareInnerObjects(int firstIndex, int secondIndex){
        String firstLine = arrayList.get(firstIndex);
        String secondLine = arrayList.get(secondIndex);
        return firstLine.compareTo(secondLine);
    }
}
