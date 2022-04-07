package com.example.application.data.service;

import com.example.application.data.entity.*;
import com.example.application.data.repository.*;
import net.bytebuddy.description.annotation.AnnotationValue;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final CrewRepository crewRepository;
    private final TrainRepository trainRepository;
    private final PassportRepository passportRepository;
    private final TicketRepository ticketRepository;
    private final RaceRepository raceRepository;
    private final KassaRepository kassaRepository;


    public CrmService(CrewRepository crewRepository, TrainRepository trainRepository, PassportRepository passportRepository, TicketRepository ticketRepository, RaceRepository raceRepository, KassaRepository kassaRepository) {
        this.crewRepository = crewRepository;
        this.trainRepository = trainRepository;
        this.passportRepository = passportRepository;
        this.ticketRepository = ticketRepository;
        this.raceRepository = raceRepository;
        this.kassaRepository = kassaRepository;
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

    public List<Race> findAllRace(String filterText) {
        if (filterText == null || filterText.isEmpty())
            return raceRepository.findAll();
        else
            return raceRepository.search(filterText);
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

    public void deleteTrain(Train train) {
        if (train == null) {
            System.err.println("[delete] Train is null.");
            return;
        }
        trainRepository.delete(train);
    }

    public void deleteRace(Race race) {
        if (race == null) {
            System.err.println("[delete] Race is null.");
            return;
        }
        raceRepository.delete(race);
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

    public void saveRace(Race race) {
        if (race == null) {
            System.err.println("[save] Race is null.");
            return;
        }
        raceRepository.save(race);
    }

    public void savePassport(Passport passport) {
        if (passport == null) {
            System.err.println("[save] Passport is null.");
            return;
        }
        passportRepository.save(passport);
    }

    public void saveTicket(Ticket ticket) {
        if (ticket == null) {
            System.err.println("[save] Ticket is null.");
            return;
        }
        ticketRepository.save(ticket);
    }

    public Kassa findKassaByNumber(long number) {
        return kassaRepository.findByNumber(number);
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
    public List<Ticket> findAllTicket() {
        return ticketRepository.findAll();
    }
    public List<Race> findAllRace() {
        return raceRepository.findAll();
    }

}