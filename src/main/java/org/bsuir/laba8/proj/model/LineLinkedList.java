package org.bsuir.laba8.proj.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class LineLinkedList {
    private LinkedList<String> linkedList;
    private int capacity;

    public LineLinkedList(){
        linkedList = new LinkedList<>();
        capacity = 0;
    }

    public void add(String line) {
        if(linkedList.size() >= capacity){
            linkedList.removeFirst();
        }
        linkedList.add(line);
    }

    public void remove(String line) {
        linkedList.remove(line);
    }

    public LinkedList<String> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(ArrayList<String> arrayList) {
        for(int i = 0; i < capacity; i++){
            linkedList.add(arrayList.get(i));
        }
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
