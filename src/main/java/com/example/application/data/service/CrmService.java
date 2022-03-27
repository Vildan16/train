package com.example.application.data.service;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Passport;
import com.example.application.data.entity.Train;
import com.example.application.data.repository.CrewRepository;
import com.example.application.data.repository.PassportRepository;
import com.example.application.data.repository.TrainRepository;
import com.example.application.views.PassportForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final CrewRepository crewRepository;
    private final TrainRepository trainRepository;
    private final PassportRepository passportRepository;

    public CrmService(CrewRepository crewRepository, TrainRepository trainRepository, PassportRepository passportRepository) {
        this.crewRepository = crewRepository;
        this.trainRepository = trainRepository;
        this.passportRepository = passportRepository;
    }

    public List<Crew> findAllCrew(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return crewRepository.findAll();
        else
            return crewRepository.search(filterText);
    }

    public List<Train> findAllTrain(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return trainRepository.findAll();
        else
            return trainRepository.search(filterText);
    }

    public List<Passport> findAllPassport(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return passportRepository.findAll();
        else
            return passportRepository.search(filterText);
    }

    public long countCrew() {
        return crewRepository.count();
    }

    public void deleteCrew(Crew crew) {
        if (crew == null) {
            System.err.println("[delete] Crew is null.");
            return;
        }
        if (crew.getTrain().getPilot().equals(crew))
            crew.getTrain().setPilot(null);
        crewRepository.delete(crew);
    }

    public void deleteTrain(Train train) {
        if (train == null) {
            System.err.println("[delete] Train is null.");
            return;
        }
        trainRepository.delete(train);
    }

    public void deletePassport(Passport passport) {
        if (passport == null) {
            System.err.println("[delete] Passport is null.");
            return;
        }
        passportRepository.delete(passport);
    }

    public void saveCrew(Crew crew) {
        if (crew == null) {
            System.err.println("[save] Crew is null.");
            return;
        }
        crewRepository.save(crew);
    }

    public void saveTrain(Train train) {
        if (train == null) {
            System.err.println("[save] Train is null.");
            return;
        }
        trainRepository.save(train);
    }

    public void savePassport(Passport passport) {
        if (passport == null) {
            System.err.println("[save] Passport is null.");
            return;
        }
        passportRepository.save(passport);
    }

    public List<Train> findAllTrain() {
        return trainRepository.findAll();
    }
    public List<Crew> findAllCrew() {
        return crewRepository.findAll();
    }
    public List<Passport> findAllPassport() {
        return passportRepository.findAll();
    }

}