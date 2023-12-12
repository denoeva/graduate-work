package ru.skypro.homework.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import ru.skypro.homework.dto.auth_register.Role;

import javax.persistence.*;

/**
 * Class with user's data in DB
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    @Enumerated
    private Role role;
}
