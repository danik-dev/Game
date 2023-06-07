package com.example.game.entities;

import com.example.game.enums.EncryptionAlgorithm;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "this field must not be blank")
    private String username;
    @NotBlank(message = "this field must not be blank")
    private String password;
    @Email(message = "enter valid email")
    private String email;
    @Enumerated(EnumType.STRING)
    private EncryptionAlgorithm algorithm;
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Statistics statistics;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", algorithm=" + algorithm +
                ", registrationDate=" + registrationDate +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

