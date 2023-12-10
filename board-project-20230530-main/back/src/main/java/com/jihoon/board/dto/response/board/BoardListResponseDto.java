package com.jihoon.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.jihoon.board.entity.BoardViewEntity;
import com.jihoon.board.entity.resultSet.BoardListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardListResponseDto {
  private int boardNumber;
  private String title;
  private String contents;
  private String imageUrl;
  private int viewCount;
  private int commentCount;
  private int favoriteCount;
  private String writeDatetime;
  private String writerProfileImage;
  private String writerNickname;

  public BoardListResponseDto(BoardListResultSet resultSet) {
    this.boardNumber = resultSet.getBoardNumber();
    this.title = resultSet.getTitle();
    this.contents = resultSet.getContents();
    this.imageUrl = resultSet.getImageUrl();
    this.viewCount = resultSet.getViewCount();
    this.commentCount = resultSet.getCommentCount();
    this.favoriteCount = resultSet.getFavoriteCount();
    this.writeDatetime = resultSet.getWriteDatetime();
    this.writerProfileImage = resultSet.getWriterProfileImage();
    this.writerNickname = resultSet.getWriterNickname();
  }

  public BoardListResponseDto(BoardViewEntity boardViewEntity) {
    this.boardNumber = boardViewEntity.getBoardNumber();
    this.title = boardViewEntity.getTitle();
    this.contents = boardViewEntity.getContents();
    this.imageUrl = boardViewEntity.getImageUrl();
    this.viewCount = boardViewEntity.getViewCount();
    this.commentCount = boardViewEntity.getCommentCount();
    this.favoriteCount = boardViewEntity.getFavoriteCount();
    this.writeDatetime = boardViewEntity.getWriteDatetime();
    this.writerProfileImage = boardViewEntity.getWriterProfileImage();
    this.writerNickname = boardViewEntity.getWriterNickname();
  }

  public static List<BoardListResponseDto> copyList(List<BoardListResultSet> resultSets) {
    List<BoardListResponseDto> boardList = new ArrayList<>();

    for (BoardListResultSet resultSet: resultSets) {
      BoardListResponseDto board = new BoardListResponseDto(resultSet);
      boardList.add(board);
    }

    return boardList;
  }

  public static List<BoardListResponseDto> copyEntityList(List<BoardViewEntity> boardViewEntities) {
    List<BoardListResponseDto> boardList = new ArrayList<>();

    for (BoardViewEntity entity: boardViewEntities) {
      BoardListResponseDto board = new BoardListResponseDto(entity);
      boardList.add(board);
    }

    return boardList;
  }
}
