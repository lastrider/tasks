package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository repository;

    @Test
    public void getAllTasks() {
        //Given
        Task task = new Task(1L, "name", "description");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //When
        when(repository.findAll()).thenReturn(tasks);
        //Then
        Assert.assertEquals(1, dbService.getAllTasks().size());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(task, dbService.getAllTasks().get(0)));
    }
    @Test
    public void getTask() {
        Task task = new Task(1L, "title", "content");
        System.out.println(task.toString());
        //When
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //Then
        Assert.assertEquals("content", dbService.getTask(1L).get().getContent());
    }
    @Test
    public void getTaskById() {
        Task task = new Task(1L, "title", "content");
        //When
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        //Then
        Assert.assertEquals("title", dbService.getTask(1L).get().getTitle());
        Assert.assertEquals("content", dbService.getTask(1L).get().getContent());
    }
    @Test
    public void saveTask() {
        Task task = new Task(1L, "title", "content");
        //When
        when(repository.save(task)).thenReturn(task);
        //Then
        Assert.assertEquals(1L, (long) dbService.saveTask(task).getId());
        Assert.assertEquals("title", dbService.saveTask(task).getTitle());
        Assert.assertEquals("content", dbService.saveTask(task).getContent());
    }

    @Test
    public void deleteTask() {
        //Given
        //When
        dbService.deleteTask(1L);
        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}