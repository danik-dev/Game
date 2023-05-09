package com.example.game.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class GameSession{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH-mm-ss")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime getStarted;
    private long duration;
    private int score;
    @ManyToOne
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    @Override
    public String toString() {
        return "GameSession{" +
                "id=" + id +
                ", getStarted=" + getStarted +
                ", duration=" + duration +
                ", score=" + score +
                '}';
    }
}
