package com.PFE.EndOfYearProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Numbers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "number_seq")
    @SequenceGenerator(name = "number_seq", sequenceName = "number_seq", allocationSize = 1)
    private long id;
    private long num;
    private String country;
    private String region;
    private String address;
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
