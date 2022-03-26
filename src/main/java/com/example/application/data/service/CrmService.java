package com.example.application.data.service;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Train;
import com.example.application.data.repository.CrewRepository;
import com.example.application.data.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final CrewRepository crewRepository;
    private final TrainRepository trainRepository;

    public CrmService(CrewRepository crewRepository, TrainRepository trainRepository) {
        this.crewRepository = crewRepository;
        this.trainRepository = trainRepository;
    }

    public List<Crew> findAllCrew(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return crewRepository.findAll();
        else
            return crewRepository.search(filterText);
    }

    public long countCrew() {
        return crewRepository.count();
    }

    public void deleteCrew(Crew crew) {
        if (crew == null) {
            System.err.println("[delete] Crew is null.");
            return;
        }
        crewRepository.delete(crew);
    }

    public void saveCrew(Crew crew) {
        if (crew == null) {
            System.err.println("[save] Crew is null.");
            return;
        }
        crewRepository.save(crew);
    }

    public List<Train> findAllTrain() {
        return trainRepository.findAll();
    }

}