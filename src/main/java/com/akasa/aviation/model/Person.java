package com.akasa.aviation.model;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name="pilot_data")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name="native",strategy="native")
    @Column(name="id")
    private int id;
    @Column(name="crewname")
    private String crewname;
    @Column(name="staffnumber")
    private String staffnumber;
    @Column(name="ranks")
    private String ranks;
    @Column(name="qualificationcode")
    private String qualificationcode;
    @Column(name="expirydate")
    private String expirydate;
    @Column(name="today")
    private String today;
    @Column(name="daysremaining")
    private int daysremaining;
    @Column(name="status")
    private String status;
    @Column(name="picforelease")
    private String picforelease;
}
