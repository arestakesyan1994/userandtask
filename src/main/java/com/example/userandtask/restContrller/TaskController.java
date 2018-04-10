package com.example.userandtask.restContrller;


import com.example.userandtask.model.Task;
import com.example.userandtask.model.User;
import com.example.userandtask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping()
    public ResponseEntity tasks(){
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity getTaskByUserId(@PathVariable(name = "userId") int id) {
        List<Task> byUserId = taskRepository.findAllByUserId(id);
        if (byUserId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with " + id + " id no found");
        }
        return ResponseEntity.ok(byUserId);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@PathVariable(name = "id") int id) {
        Task one = taskRepository.findOne(id);
        if (one == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with " + id + " id no found");
        }
        return ResponseEntity.ok(one);
    }

    @GetMapping("/byStartDate/{date}")
    public ResponseEntity getTaskByTime(@PathVariable(name = "date") Date date) {
        List<Task> startDate = taskRepository.findAllByStartDate(date);
        if (startDate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with " + date + " id no found");
        }
        return ResponseEntity.ok(startDate);
    }

    @PostMapping()
    public ResponseEntity saveUser(@RequestBody Task task) {
        taskRepository.save(task);
        return ResponseEntity.ok("created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTaskById(@PathVariable(name = "id") int id) {
        try {
            taskRepository.delete(id);
            return ResponseEntity.ok("Delete");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("task with " + id + "does not exist");
        }
    }
}
