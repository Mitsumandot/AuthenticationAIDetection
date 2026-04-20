package com.example.authenticationforaidetection.services;


import com.example.authenticationforaidetection.dto.PredictionRequest;
import com.example.authenticationforaidetection.entities.History;
import com.example.authenticationforaidetection.models.ModelClient;
import com.example.authenticationforaidetection.responses.PredictionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictionService {
    private final ModelClient modelClient;
    private final HistoryService historyService;

    public PredictionService(ModelClient modelClient, HistoryService historyService){
        this.modelClient = modelClient;
        this.historyService = historyService;
    }

    public List<PredictionResponse> predict(List<PredictionRequest> predictionRequests, String link){
        List<PredictionResponse> responses = modelClient.predict(predictionRequests);
        History history = historyService.addHistory(responses, link);
        return responses;
    }
}
