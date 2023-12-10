package com.jihoon.board.dto.request.board;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {
  @NotBlank
  private String title;

  @NotBlank
  private String contents;

  private String imageUrl;
}
