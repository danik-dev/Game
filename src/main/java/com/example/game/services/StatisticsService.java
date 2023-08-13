package com.example.game.services;

import com.example.game.dto.LeaderDto;
import com.example.game.dto.PersonalStatisticsDto;
import com.example.game.entities.GameSession;
import com.example.game.entities.Statistics;
import com.example.game.entities.User;
import com.example.game.repos.StatisticsRepository;
import com.example.game.repos.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
    private final StatisticsRepository statisticsRepository;
    private final UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public StatisticsService(StatisticsRepository statisticsRepository, UserRepository userRepository) {
        this.statisticsRepository = statisticsRepository;
        this.userRepository = userRepository;
    }

    public List<LeaderDto> getLeadersByScore() {
        List<Statistics> statisticsList = statisticsRepository.findAll().stream()
                .sorted(Comparator.comparing(Statistics::getMaxScore).reversed())
                .limit(50).toList();
        return statisticsList.stream().map(s -> new LeaderDto(s.getUser().getUsername(),
                s.getMaxScore(), s.getFastestWin(), s.getUser().getRegistrationDate()))
                .sorted(Comparator.comparingInt(LeaderDto::getMaxScore).reversed()).collect(Collectors.toList());
    }
    public List<LeaderDto> getLeadersByTime() {
        List<Statistics> statisticsList = statisticsRepository.findAll().stream()
                .sorted(Comparator.comparing(Statistics::getFastestWin))
                .limit(50).toList();
        return statisticsList.stream().map(s -> new LeaderDto(s.getUser().getUsername(),
                        s.getMaxScore(), s.getFastestWin(), s.getUser().getRegistrationDate()))
                .sorted(Comparator.comparing(LeaderDto::getFastestWin)).collect(Collectors.toList());
    }

    public PersonalStatisticsDto getPersonalStatistics(String token) {
        User user = userRepository.findUserByUsername(token).get();
        Statistics statistics = user.getStatistics();
        return  new PersonalStatisticsDto(
                user.getUsername(), statistics.getAllTimePlayTime(),
                statistics.getAllTimeScore(), statistics.getFastestWin(),
                statistics.getMaxScore(), statistics.getGameSessions()
                .stream().sorted(Comparator.comparing(GameSession::getGetStarted).reversed()).toList()
        );
    }
}
