package com.PFE.EndOfYearProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="USERS")
@EntityListeners(AuditingEntityListener.class)
public class Users  implements UserDetails, Principal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String fullname;
    @Column(unique = true)
    private String email;
    private String password;
    private String poste ;
    private boolean accountLocked;
    private boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contacts> contacts = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Numbers number;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SMS> sms = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Calls> appels = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private roles role;
    /*@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "USERS_GROUPES",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "group_id",referencedColumnName = "ID")
    )
    private List<Groupes> groups= new ArrayList<>();*/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id") // This will be the foreign key in the User table
    private Groupes groups;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role != null) {
            return List.of(new SimpleGrantedAuthority(this.role.getName()));
        } else {
            return List.of();
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    public String getFullName() {
        return fullname;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


    @Override
    public String getName() {
        return email;
    }

}
