package com.example.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaderDto {
    private String username;
    private int maxScore;
    private long fastestWin;
    private LocalDate registrationDate;

}
