package com.example.game.dto;

import com.example.game.entities.GameSession;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonalStatisticsDto {
    private String username;
    private long allTimePlayTime;
    private long allTimeScore;
    private long fastestWin;
    private int maxScore;
    private List<GameSession> gameSessions;
}
