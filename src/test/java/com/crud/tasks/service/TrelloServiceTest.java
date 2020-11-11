package com.crud.tasks.service;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.bouncycastle.crypto.engines.EthereumIESEngine;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService emailService;

    @Test
    public void fetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //Then
        Assert.assertTrue(trelloService.fetchTrelloBoards().size()>0);
    }

    @Test
    public void createTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        //When
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);
        //Then
        Assert.assertTrue(trelloService.createTrelloCard(trelloCardDto) == null);
    }
    @Test
    public void createTrelloCard2() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto();
        TrelloCardDto trelloCardDto = new TrelloCardDto();
        //When
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);
        //Then
        Assert.assertTrue(trelloService.createTrelloCard(trelloCardDto) == null);
    }

}