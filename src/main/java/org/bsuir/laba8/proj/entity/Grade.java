package org.bsuir.laba8.proj.entity;

import lombok.Data;

enum GRADE {
    GREAT(5, "отлично"),
    GOOD(4, "хорошо"),
    OK(3, "удовлетворительно"),
    NOTBAD(2, "неудовлетворительно"),
    BAD(1, "плохо");

    public int value;
    public String name;

    GRADE(int value, String name){
        this.name = name;
        this.value = value;
    }
}

@Data
public class Grade {
    private Integer value;
    private String equivalent;
}
