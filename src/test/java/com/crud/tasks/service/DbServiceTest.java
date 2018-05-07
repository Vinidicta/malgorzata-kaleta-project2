package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    private static final Task task1 = new Task(1L, "test", "test content");
    private static final Task task2 = new Task(2L, "test", "test content");
    private static final List<Task> taskList = Arrays.asList(task1, task2);

    @Test
    public void testGetOptionalTaskById() {
        //Given
        when(repository.findById(3L)).thenReturn(Optional.empty());

        //When
        Optional<Task> result = dbService.getTask(3L);

        //Then
        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testGetAllTasks() {
        //Given
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> tasksList = dbService.getAllTasks();

        //Then2
        assertEquals(2, tasksList.size());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task = new Task(3L, "title", "content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(task.getId(), savedTask.getId());
        assertEquals(task.getTitle(), savedTask.getTitle());
        assertEquals(task.getContent(), savedTask.getContent());
    }

    @Test
    public void deleteTaskTest() {
        //Given
        //When
        dbService.deleteTask(2L);

        //Then
        verify(repository, times(1)).deleteById(2L);
    }
}