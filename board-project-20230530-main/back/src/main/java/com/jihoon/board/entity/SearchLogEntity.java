package com.jihoon.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="search_log")
@Table(name="search_log")
public class SearchLogEntity {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int logSequence;
  private String searchWord;
  private String relationWord;

  public SearchLogEntity(String searchWord, String relationWord) {
    this.searchWord = searchWord;
    this.relationWord = relationWord;
  }
}
