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
    private Integer id;
    private Integer num;
    private String country;
    private String region;
    private String address;
    private String type;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

}
