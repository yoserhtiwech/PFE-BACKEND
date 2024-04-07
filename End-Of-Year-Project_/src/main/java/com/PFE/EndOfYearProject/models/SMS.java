package com.PFE.EndOfYearProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="SMS")
public class SMS {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sms_seq")
    @SequenceGenerator(name = "sms_seq", sequenceName = "sms_seq", allocationSize = 1)
    private long  id;
    private  String type ;
    private Timestamp time;
    private String content;
    @ManyToOne
    @JoinColumn(name = "iduser")
    private Users user;
    @ManyToOne
    @JoinColumn(name = "idcontact")
    private Contacts contact;


}
