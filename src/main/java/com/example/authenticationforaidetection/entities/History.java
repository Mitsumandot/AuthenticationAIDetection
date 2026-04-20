package com.example.authenticationforaidetection.entities;


import jakarta.persistence.*;

@Entity

public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String link;

    private float AIMeanPrediction;
}
