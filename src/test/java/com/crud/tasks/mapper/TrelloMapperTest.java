package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void mapToBoards() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("id1", "name1", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("name1", "id1", trelloListsDto);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        Assert.assertEquals("id1", trelloBoards.get(0).getId());
        Assert.assertEquals("name1", trelloBoards.get(0).getName());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new TrelloList("id1", "name1", false), trelloBoards.get(0).getLists().get(0)));
    }

    @Test
    public void mapToBoardsDto() {
        //Given
        TrelloList trelloList = new TrelloList("id2", "name2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("id2", "name2", trelloLists);
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        Assert.assertEquals("id2", trelloBoardDtos.get(0).getId());
        Assert.assertEquals("name2", trelloBoardDtos.get(0).getName());
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new TrelloListDto("id2", "name2", false), trelloBoardDtos.get(0).getLists().get(0)));
    }

    @Test
    public void mapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("id1", "name1", false);
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(trelloListDto);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new TrelloList("id1", "name1", false), trelloLists.get(0)));
        Assert.assertEquals(1,trelloLists.size());

    }

    @Test
    public void mapToListDto() {
        //Give
        TrelloList trelloList = new TrelloList("id2", "name2", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        //When
        List<TrelloListDto> trelloListDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        Assert.assertTrue(EqualsBuilder.reflectionEquals(new TrelloListDto("id2", "name2", false), trelloListDto.get(0)));
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1", "pos1", "id1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        Assert.assertTrue(EqualsBuilder.reflectionEquals(
                new TrelloCard("name1", "description1", "pos1", "id1"), trelloCard));
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCard c = new TrelloCard("name2", "description2", "pos2", "id2");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(c);
        //Then
        Assert.assertTrue(EqualsBuilder.reflectionEquals(
                new TrelloCardDto("name2", "description2", "pos2", "id2"), trelloCardDto));

    }
}