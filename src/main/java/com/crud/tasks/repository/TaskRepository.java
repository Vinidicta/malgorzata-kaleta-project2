package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    @Override
    long count();

    void deleteById(Long id);

    Optional<Task> findById(Long id);


}
