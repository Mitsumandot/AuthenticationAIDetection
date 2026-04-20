package com.example.authenticationforaidetection.services;

import com.example.authenticationforaidetection.entities.History;
import com.example.authenticationforaidetection.entities.User;
import com.example.authenticationforaidetection.repositories.HistoryRepository;
import com.example.authenticationforaidetection.responses.PredictionResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    public History addHistory(List<PredictionResponse> predictionResponses, String link){
        double AIMean = 0;
        for (PredictionResponse pr : predictionResponses) {
            if ("LABEL_0".equals(pr.getLabel())) {
                AIMean += pr.getPrediction();
            } else if ("LABEL_1".equals(pr.getLabel())) {
                AIMean += (1.0 - pr.getPrediction());
            }
        }
        AIMean = AIMean / predictionResponses.size();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        History history = new History();
        history.setAIMeanPrediction(AIMean);
        history.setUser(user);
        history.setLink(link);
        return historyRepository.save(history);
    }
}
