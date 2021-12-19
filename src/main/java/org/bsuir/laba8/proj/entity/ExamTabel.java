package org.bsuir.laba8.proj.entity;

import lombok.Data;
import java.util.ArrayList;
import java.util.Date;

@Data
public class ExamTabel {
    private String tabelId;
    private String subjectName;
    private String examiner;
    private Date examDate;
    private ArrayList<Student> gradesList;

    public ExamTabel(){
        gradesList = new ArrayList<Student>();
    }
}
