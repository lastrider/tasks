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

import static java.util.Optional.of;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        //Given
        Task task = new Task(1L, "name", "description");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        //When
        when(taskRepository.findAll()).thenReturn(tasks);
        //Then
        Assert.assertEquals(1, dbService.getAllTasks().size());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(task, dbService.getAllTasks().get(0)));
    }
/*
    @Test
    public void getTaskById() {
        Task task = new Task(1L, "title", "content");
        //When
        //when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        //Then
        Assert.assertEquals("title", dbService.getTaskById(1L).getTitle());
        Assert.assertEquals("description", dbService.getTaskById(1L).getContent());

    }
    @Test
    public void getTask() {
        Task task = new Task(1L, "title", "content");
        //When
        when(taskRepository.findById(1L)).thenReturn(task);
        //Then
        Assert.assertTrue(dbService.getTaskById(1L).);
        Assert.assertEquals("description", dbService.getTaskById(1L).getContent());

    }*/

    @Test
    public void saveTask() {
        Task task = new Task(1L, "title", "content");
        //When
        when(taskRepository.save(task)).thenReturn(task);
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
        verify(taskRepository, times(1)).deleteById(1L);
    }
}