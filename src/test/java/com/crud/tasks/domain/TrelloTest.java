package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;

public class TrelloTest {

    @Test
    public void getBoard() {
        //Given
        Trello trello = new Trello(1, 2);
        //When
        int board = trello.getBoard();
        //Then
        Assert.assertEquals(1, board);
    }

    @Test
    public void getCard() {
        //Given
        Trello trello = new Trello(1, 2);
        //When
        int card = trello.getCard();
        //Then
        Assert.assertEquals(2, card);
    }
}