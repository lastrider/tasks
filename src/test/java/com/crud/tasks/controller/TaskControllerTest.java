package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldFetchEmptyTasks() throws Exception {
        //Given
        List<Task> tasks = new ArrayList<>();
        List<TaskDto> taskDtos = new ArrayList<>();
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        //When&Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        TaskDto taskDto1 = new TaskDto(1L, "title1", "content1");
        TaskDto taskDto2 = new TaskDto(2L, "title2", "content2");
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(taskDto1);
        taskDtos.add(taskDto2);
        when(dbService.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(taskDtos);
//When&Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title1")))
                .andExpect(jsonPath("$[0].content", is("content1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("title2")))
                .andExpect(jsonPath("$[1].content", is("content2")));
    }

    @Test
    public void shouldFetchTask() throws Exception {
        //Given
        Task task = new Task(1L, "test title", "test content");
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");

        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("test title")))
                .andExpect(jsonPath("$.content", is("test content")));
    }

    @Test
    public void deleteTask() throws Exception {
        //Given
        when(dbService.getTask(1L)).thenReturn(java.util.Optional.of( new Task()));
        //When&Then
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        String jsonContent = new Gson().toJson(taskDto);

        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L, "title1", "content1");
        String jsonContent = new Gson().toJson(taskDto);

        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));
    }
}
