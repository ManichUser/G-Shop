package com.example.authentification_test.entities;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String adresse;
    private boolean enabled = true;
    private LocalDateTime createAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "role_active", nullable = false)
    private Role currentRole;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Set<Role> availableRoles;

    //verifie le role d'utilisateur lors du switch account
    public boolean hasRole(Role role){
        return availableRoles != null && availableRoles.contains(role);
    }

    public void switchRole(Role newRole){
        if (hasRole(newRole)){
            this.currentRole = newRole;
        } else{
            throw new IllegalArgumentException("Role non autorise pour cet utilisateur");
        }
    }
}
