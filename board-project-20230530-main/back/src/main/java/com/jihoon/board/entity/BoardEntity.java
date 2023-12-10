package com.jihoon.board.entity;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jihoon.board.dto.request.board.PatchBoardRequestDto;
import com.jihoon.board.dto.request.board.PostBoardRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int boardNumber;
  private String title;
  private String contents;
  private String imageUrl;
  private int viewCount;
  private int commentCount;
  private int favoriteCount;
  private String writeDatetime;
  private String writerEmail;

  public BoardEntity(String writerEmail, PostBoardRequestDto dto) {
    Date now = Date.from(Instant.now());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String writeDatetime = simpleDateFormat.format(now);

    this.title = dto.getTitle();
    this.contents = dto.getContents();
    this.imageUrl = dto.getImageUrl();
    this.writeDatetime = writeDatetime;
    this.writerEmail = writerEmail;
  }

  public void patch(PatchBoardRequestDto dto) {
    this.title = dto.getTitle();
    this.contents = dto.getContents();
    this.imageUrl = dto.getImageUrl();
  }

  public void increaseViewCount() {
    this.viewCount++;
  }

  public void increaseCommentCount() {
    this.commentCount++;
  }

  public void increaseFavoriteCount() {
    this.favoriteCount++;
  }

  public void decreaseFavoriteCount() {
    this.favoriteCount--;
  }
}
