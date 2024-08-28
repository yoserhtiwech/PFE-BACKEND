package com.PFE.EndOfYearProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="GROUPES")
public class Groupes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupes_seq")
    @SequenceGenerator(name = "groupes_seq", sequenceName = "groupes_seq", allocationSize = 1)
    private  Integer id ;
    private String name;
    @OneToMany(mappedBy = "groups",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> users;


}
