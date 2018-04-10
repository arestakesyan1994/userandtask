package com.example.userandtask.repository;

import com.example.userandtask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findAllByUserId(int id);
    List<Task> findAllByStartDate(Date date);

}
