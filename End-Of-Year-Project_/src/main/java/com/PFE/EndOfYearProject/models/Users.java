package com.PFE.EndOfYearProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="USERS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String username;
    private String email;
    private String password;
    private String poste ;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contacts> contacts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Numbers> numbers = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SMS> sms = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Appels> appels = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "user_id" ,referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "ID")})


    private List<roles> roles= new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "USERS_GROUPES",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "group_id",referencedColumnName = "ID")
    )
    private List<Groupes> groups= new ArrayList<>();

}
