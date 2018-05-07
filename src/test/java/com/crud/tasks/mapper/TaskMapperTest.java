package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    private TaskMapper taskMapper;

    private static final Task task = new Task(1L, "test", "test content");
    private static final TaskDto taskDto = new TaskDto(1L, "test", "test content");

    @Test
    public void testMapToTask() {
        //Given
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task.getId(), mappedTask.getId());
        assertEquals(task.getTitle(), mappedTask.getTitle());
        assertEquals(task.getContent(), mappedTask.getContent());
    }

    @Test
    public void mapToTaskDtoTest() {
        //Given
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(taskDto.getId(), mappedTaskDto.getId());
        assertEquals(taskDto.getTitle(), mappedTaskDto.getTitle());
        assertEquals(taskDto.getContent(), mappedTaskDto.getContent());
    }

    @Test
    public void testMapTaskDtoList() {
        //Given
        List<TaskDto> taskDtoList = Arrays.asList(taskDto);
        List<Task> taskList = Arrays.asList(task);

        //When
        List<TaskDto> mappedList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertNotNull(mappedList);
        assertEquals(1, mappedList.size());

        mappedList.forEach(task -> {
            assertEquals(taskDto.getId(), task.getId());
            assertEquals(taskDto.getTitle(), task.getTitle());
            assertEquals(taskDto.getContent(), task.getContent());
        });
    }
}