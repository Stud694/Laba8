package org.bsuir.laba8.proj.dao;

import org.bsuir.laba8.proj.entity.ExamTabel;

import java.util.List;
import java.util.Optional;

public class ExamTabelDAO implements Dao<ExamTabel> {
    @Override
    public Optional<ExamTabel> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<ExamTabel> getAll() {
        return null;
    }

    @Override
    public void save(ExamTabel examTable) {

    }

    @Override
    public void update(ExamTabel examTable, String[] params) {

    }

    @Override
    public void delete(ExamTabel examTable) {

    }
}
