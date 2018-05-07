package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDto> getTasks() {
        final List<Task> tasksList = dbService.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasksList);
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTasks(@PathVariable("taskId") Long taskId) throws TaskNotFoundException {
        final Task displayedTask = dbService.getTask(taskId).orElseThrow(TaskNotFoundException::new);
        return taskMapper.mapToTaskDto(displayedTask);
    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {
        dbService.deleteTask(taskId);
    }

    @PutMapping
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        final Task updatedTask = dbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(updatedTask);
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {
        dbService.saveTask(taskMapper.mapToTask(taskDto));
    }
}
