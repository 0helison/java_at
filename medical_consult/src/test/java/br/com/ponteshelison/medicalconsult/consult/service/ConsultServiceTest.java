package br.com.ponteshelison.medicalconsult.consult.service;

import br.com.ponteshelison.medicalconsult.consult.domain.Consult;
import br.com.ponteshelison.medicalconsult.consult.repositories.ConsultRepository;
import br.com.ponteshelison.medicalconsult.consult.services.ConsultService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultServiceTest {
    @InjectMocks
    private ConsultService consultService;

    @Mock
    private ConsultRepository consultRepository;

    @Test
    void registerConsult(){
        Consult consult = new Consult();
        consult.setProfessional("Dr. Carlos");

        when(consultRepository.save(any(Consult.class))).thenReturn(consult);

        var result = consultService.registerConsult(consult);

        // Validation
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Dr. Carlos", result.getProfessional())
        );

    }

    @Test
    void listConsults() {
        Consult consult1 = new Consult();
        Consult consult2 = new Consult();
        consult1.setProfessional("Dr. Carlos 1");
        consult2.setProfessional("Dr. Carlos 2");

        List<Consult> consults = new ArrayList<>();
        consults.add(consult1);
        consults.add(consult2);

        when(consultRepository.findAll()).thenReturn(consults);

        var result = consultService.listConsults();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Dr. Carlos 1", result.get(0).getProfessional()),
                () -> assertEquals("Dr. Carlos 2", result.get(1).getProfessional())
        );
    }


    @Test
    void showConsult(){
        Consult consult = new Consult();
        consult.setProfessional("Dr. Carlos");

        when(consultRepository.findById(consult.getConsultId())).thenReturn(Optional.of(consult));

        var result = consultService.showConsult(consult.getConsultId());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("Dr. Carlos", consult.getProfessional())
        );
    }

    @Test
    void updateConsult() {

        Long consultId = 1L;
        Consult existingConsult = new Consult();
        existingConsult.setConsultId(consultId);
        existingConsult.setProfessional("Dr. Carlos");

        Consult updatedConsult = new Consult();
        updatedConsult.setConsultId(consultId);
        updatedConsult.setProfessional("Dr. Ana");
        updatedConsult.setSpecialty("Cardiology");

        when(consultRepository.findById(consultId)).thenReturn(Optional.of(existingConsult));
        when(consultRepository.save(existingConsult)).thenReturn(existingConsult);

        Consult result = consultService.updateConsult(updatedConsult);

        assertNotNull(result);
        assertEquals("Dr. Ana", result.getProfessional());
        assertEquals("Cardiology", result.getSpecialty());
        verify(consultRepository, times(1)).findById(consultId);
        verify(consultRepository, times(1)).save(existingConsult);
    }


    @Test
    void deleteConsult() {
        Long consultId = 1L;
        Consult consult = new Consult();
        consult.setConsultId(consultId);

        when(consultRepository.findById(consultId)).thenReturn(Optional.of(consult));

        consultService.deleteConsult(consultId);

        verify(consultRepository, times(1)).findById(consultId);
        verify(consultRepository, times(1)).deleteById(consultId);
    }

}