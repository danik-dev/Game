package com.example.game.services;

import com.example.game.entities.GameSession;
import com.example.game.entities.Statistics;
import com.example.game.repos.GameSessionRepository;
import com.example.game.repos.UserRepository;
import com.example.game.security.CustomUserDetails;
import com.example.game.security.JpaUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameSessionService {
    private final GameSessionRepository gameSessionRepository;
    private final UserRepository userRepository;

    public GameSessionService(GameSessionRepository gameSessionRepository, UserRepository userRepository) {
        this.gameSessionRepository = gameSessionRepository;
        this.userRepository = userRepository;
    }

    public void saveGameSession(GameSession gameSession, String token) {
//        CustomUserDetails cud = (CustomUserDetails) jpaUserDetailsService.loadUserByUsername(token);
//        Statistics statistics = cud.getUser().getStatistics();
        Statistics statistics = userRepository.findUserByUsername(token).get().getStatistics();
        statistics.setAllTimePlayTime(statistics.getAllTimePlayTime() + gameSession.getDuration());
        statistics.setAllTimeScore(statistics.getAllTimeScore() + gameSession.getScore());
        if (statistics.getFastestWin() > gameSession.getDuration()) {
            statistics.setFastestWin(gameSession.getDuration());
        }
        if (statistics.getMaxScore() < gameSession.getScore()) {
            statistics.setMaxScore(gameSession.getScore());
        }
        gameSession.setStatistics(statistics);
        gameSessionRepository.save(gameSession);
    }
}
