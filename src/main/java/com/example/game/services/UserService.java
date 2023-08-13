package com.example.game.services;

import com.example.game.entities.Statistics;
import com.example.game.entities.User;
import com.example.game.enums.EncryptionAlgorithm;
import com.example.game.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setAlgorithm(EncryptionAlgorithm.BCRYPT);
        user.setRegistrationDate(LocalDate.now());
        Statistics statistics = new Statistics();
        statistics.setUser(user);
        statistics.setFastestWin(999999999);
        user.setStatistics(statistics);
        userRepository.save(user);
    }
}
