package com.entities;

import java.util.Date;

import com.services.impl.enums.JobType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Artist {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    private JobType job;
    private Date birthday;
    private String biography;
}