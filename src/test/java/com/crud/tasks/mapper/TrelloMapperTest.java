package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoardsAndMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("testId", "test List", false);
        List<TrelloListDto> listDto = Arrays.asList(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("testId", "test Board", listDto);
        List<TrelloBoardDto> trelloBoardsList = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> mappedBoard = trelloMapper.mapToBoards(trelloBoardsList);

        //Then
        assertEquals(trelloBoardsList.get(0).getName(), mappedBoard.get(0).getName());
        assertEquals(trelloBoardsList.get(0).getName(), mappedBoard.get(0).getName());

        assertEquals(trelloBoardsList.get(0).getLists().get(0).getId(), mappedBoard.get(0).getLists().get(0).getId());
        assertEquals(trelloBoardsList.get(0).getLists().get(0).getName(), mappedBoard.get(0).getLists().get(0).getName());
        assertEquals(trelloBoardsList.get(0).getLists().get(0).isClosed(), mappedBoard.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDtoAndMapListDto() {
        //Given
        TrelloList trelloList = new TrelloList("test Id", "test List", false);
        List<TrelloList> list = Arrays.asList(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("test Id", "test Board", list);
        List<TrelloBoard> board = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> mappedBoardDto = trelloMapper.mapToBoardsDto(board);

        //Then
        assertEquals(board.get(0).getId(), mappedBoardDto.get(0).getId());
        assertEquals(board.get(0).getName(), mappedBoardDto.get(0).getName());
        assertEquals(board.get(0).getLists().get(0).getId(), mappedBoardDto.get(0).getLists().get(0).getId());
        assertEquals(board.get(0).getLists().get(0).getName(), mappedBoardDto.get(0).getLists().get(0).getName());
        assertEquals(board.get(0).getLists().get(0).isClosed(), mappedBoardDto.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test card", "test description",
                "up", "test Id");
        //When
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), mappedCard.getName());
        assertEquals(trelloCardDto.getDescription(), mappedCard.getDescription());
        assertEquals(trelloCardDto.getPos(), mappedCard.getPos());
        assertEquals(trelloCardDto.getListId(), mappedCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test card", "testing description",
                "top", "test Id");
        //When
        TrelloCardDto mappedCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(trelloCard.getName(), mappedCardDto.getName());
        assertEquals(trelloCard.getDescription(), mappedCardDto.getDescription());
        assertEquals(trelloCard.getPos(), mappedCardDto.getPos());
        assertEquals(trelloCard.getListId(), mappedCardDto.getListId());
    }
}