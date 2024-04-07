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
    private  long id ;
    private String name;
    private long superv;
    @ManyToMany(mappedBy = "groups")
    private List<Users> users;


}
