package br.com.ponteshelison.medicalconsult.consult.services;

import br.com.ponteshelison.medicalconsult.consult.domain.Consult;
import br.com.ponteshelison.medicalconsult.consult.repositories.ConsultRepository;
import br.com.ponteshelison.medicalconsult.exception.ExceptionDataIntegrityViolation;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultService {
    @Autowired
    private ConsultRepository consultRepository;

    public Consult registerConsult(Consult consult) {
        consult.setConsultId(null);
        return consultRepository.save(consult);
    }

    public List<Consult> listConsults() {
        return consultRepository.findAll();
    }

    public Consult showConsult(Long id) {
        Optional<Consult> consult = consultRepository.findById(id);
        return consult.orElseThrow(
                () -> new ObjectNotFoundException("Consult not found! ID: ", id)
        );
    }

    public void deleteConsult(Long id) {
        showConsult(id);
        try {
            consultRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ExceptionDataIntegrityViolation("Cannot delete because there are related entities.");
        }
    }

    public Consult updateConsult(Consult consult) {
        Consult newConsult = showConsult(consult.getConsultId());
        updateData(newConsult, consult);
        return consultRepository.save(newConsult);
    }

    private void updateData(Consult newConsult, Consult consult) {
        newConsult.setConsultDate(consult.getConsultDate());
        newConsult.setProfessional(consult.getProfessional());
        newConsult.setSpecialty(consult.getSpecialty());
    }

}
