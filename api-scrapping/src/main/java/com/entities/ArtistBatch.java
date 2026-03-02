package com.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ArtistBatch {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imported;
    private Integer skipped;
    private List<Artist> artists;
}