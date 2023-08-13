package com.example.game.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long allTimePlayTime;
    private long allTimeScore;
    private long fastestWin;
    private int maxScore;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "statistics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GameSession> gameSessions;

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                ", allTimePlayTime=" + allTimePlayTime +
                ", allTimeScore=" + allTimeScore +
                ", fastestWin=" + fastestWin +
                ", maxScore=" + maxScore +
                '}';
    }
}
