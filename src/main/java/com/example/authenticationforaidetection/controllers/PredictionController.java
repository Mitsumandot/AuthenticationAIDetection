package com.example.authenticationforaidetection.controllers;


import com.example.authenticationforaidetection.dto.PredictionInput;
import com.example.authenticationforaidetection.dto.PredictionRequest;
import com.example.authenticationforaidetection.responses.PredictionResponse;
import com.example.authenticationforaidetection.services.PredictionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prediction")
@AllArgsConstructor
public class PredictionController {
    private final PredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestBody PredictionInput predictionInput){
        List<PredictionResponse> predictionResponses =
                predictionService.predict(predictionInput.getPredictionRequests(),
                        predictionInput.getLink());
        return ResponseEntity.ok(predictionResponses);
    }


}
