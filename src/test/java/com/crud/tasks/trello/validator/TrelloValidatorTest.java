package com.crud.tasks.trello.validator;

import com.crud.tasks.controller.TrelloController;
import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTest {

    @InjectMocks
    TrelloValidator trelloValidator;

    @Mock
    TrelloValidator trelloValidator1;

    @Test
    public void validateTrelloBoards() {
        //Given
        TrelloList trelloList = new TrelloList("Id", "Name", false);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "name1", trelloLists);
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "test", trelloLists);
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(trelloBoard1);
        trelloBoardList.add(trelloBoard2);
        //When
        List<TrelloBoard> result = trelloValidator.validateTrelloBoards(trelloBoardList);
        //Then
        Assert.assertEquals(1, result.size());

    }
}