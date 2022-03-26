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

    public CrmService(CrewRepository crewRepository,
                      TrainRepository trainRepository) {
        this.crewRepository = crewRepository;
        this.trainRepository = trainRepository;
    }

    public List<Crew> findAllCrew(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return crewRepository.findAll();
        } else {
            return crewRepository.search(stringFilter);
        }
    }

    public long countCrew() {
        return crewRepository.count();
    }

    public void deleteCrew(Crew crew) {
        crewRepository.delete(crew);
    }

    public void saveContact(Crew crew) {
        if (crew == null) {
            System.err.println("Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        crewRepository.save(crew);
    }

    public List<Train> findAllTrains() {
        return trainRepository.findAll();
    }

}