package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {

    @InjectMocks
    TaskMapper taskMapper;

    @Test
    public void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"A", "B");
        //When
        Task task = taskMapper.mapToTask(taskDto);
        //Then
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new Task(1L, "A", "B"), task));
    }
    @Test
    public void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L,"A", "B");
        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);
        //Then
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new Task(1L, "A", "B"), task));
    }

    @Test
    public void mapToTaskDtoListTest() {
        //Given
        Task task = new Task(1L,"A", "B");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task);
        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        taskDtoList.forEach(taskDto -> {
            Assert.assertEquals((Long)1L,taskDto.getId());
            Assert.assertEquals("A", taskDto.getTitle());
            Assert.assertEquals("B", taskDto.getContent());

        });
    }
}