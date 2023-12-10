package com.jihoon.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.jihoon.board.entity.resultSet.CommentListResultSet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentListResponseDto {
  private String profileImageUrl;
  private String nickname;
  private String writeDatetime;
  private String contents;

  public CommentListResponseDto(CommentListResultSet resultSet) {
    this.profileImageUrl = resultSet.getProfileImageUrl();
    this.nickname = resultSet.getNickname();
    this.writeDatetime = resultSet.getWriteDatetime();
    this.contents = resultSet.getContents();
  }

  public static List<CommentListResponseDto> copyList(List<CommentListResultSet> resultSets) {

    List<CommentListResponseDto> commentList = new ArrayList<>();

    for (CommentListResultSet resultSet: resultSets) {
      CommentListResponseDto commentItem = new CommentListResponseDto(resultSet);
      commentList.add(commentItem);
    }

    return commentList;

  }
}
