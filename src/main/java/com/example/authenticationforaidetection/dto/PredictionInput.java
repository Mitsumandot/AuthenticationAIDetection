package com.example.authenticationforaidetection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionInput {
    private List<PredictionRequest> predictionRequests;

    private String link;

}
