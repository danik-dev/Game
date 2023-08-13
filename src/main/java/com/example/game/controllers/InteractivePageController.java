package com.example.game.controllers;

import com.example.game.dto.PersonalStatisticsDto;
import com.example.game.entities.GameSession;
import com.example.game.services.GameSessionService;
import com.example.game.services.StatisticsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InteractivePageController {
    private final GameSessionService gameSessionService;

    private final StatisticsService statisticsService;

    public InteractivePageController(GameSessionService gameSessionService, StatisticsService statisticsService) {
        this.gameSessionService = gameSessionService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/leaderboard")
    public String leaderboard(Model model){
        model.addAttribute("leadersByScore", statisticsService.getLeadersByScore());
        model.addAttribute("leadersByTime", statisticsService.getLeadersByTime());
        return "leaderboard";
    }

    @GetMapping("/statistics")
    public String statistics(@AuthenticationPrincipal String token, Model model) {
        PersonalStatisticsDto psd = statisticsService.getPersonalStatistics(token);
        model.addAttribute("personalGames", psd.getGameSessions());
        psd.setGameSessions(null);
        model.addAttribute("personalStatistics", psd);
        return "statistics";
    }
    @GetMapping("/edit-profile")
    public String editProfile() {
        return "edit-profile";
    }

    @PostMapping(value = "/game-session-results", consumes = "application/json")
    public String gameSessionResults(@RequestBody GameSession gameSession, @AuthenticationPrincipal String token) {
        gameSessionService.saveGameSession(gameSession, token);
        return "dashboard";
    }

}
