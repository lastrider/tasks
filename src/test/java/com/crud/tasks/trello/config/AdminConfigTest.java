package com.crud.tasks.trello.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminConfigTest {

    @InjectMocks
    AdminConfig adminConfig;

    @Test
    public void getAdminMail() {
        //Given
        //When
        //Then
        Assert.assertNull(adminConfig.getAdminMail());
    }
}