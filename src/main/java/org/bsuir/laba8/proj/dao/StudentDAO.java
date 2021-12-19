package org.bsuir.laba8.proj.dao;

import org.bsuir.laba8.proj.entity.Student;

import java.util.List;
import java.util.Optional;

public class StudentDAO implements Dao<Student> {
    @Override
    public Optional<Student> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public void save(Student student) {

    }

    @Override
    public void update(Student student, String[] params) {

    }

    @Override
    public void delete(Student student) {

    }
}
