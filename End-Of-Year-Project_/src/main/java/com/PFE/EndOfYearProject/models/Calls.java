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
@Table(name ="APPEL")
public class Calls {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appel_seq")
    @SequenceGenerator(name = "appel_seq", sequenceName = "appel_seq", allocationSize = 1)
    private long id ;
    @Lob
    private byte[] recording ;
    @ManyToOne
    @JoinColumn(name = "iduser")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "idcontact")
    private Contacts contact;
    private String note ;
}
