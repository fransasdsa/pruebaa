package com.upu.msthesisservice;

import com.upu.msthesisservice.dto.AdvisorDto;
import com.upu.msthesisservice.dto.ResearchLineDto;
import com.upu.msthesisservice.dto.StudentDto;
import com.upu.msthesisservice.event.ThesisEvent;
import com.upu.msthesisservice.model.Thesis;
import com.upu.msthesisservice.repository.ThesisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.stream.function.StreamBridge;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ThesisServiceTest {

    @Mock
    private ThesisRepository thesisRepository;

    @Mock
    private StudentClient studentClient;

    @Mock
    private AdvisorClient advisorClient;

    @Mock
    private ResearchLineClient researchLineClient;

    @Mock
    private DocumentClient documentClient;

    @Mock
    private StreamBridge streamBridge;

    @InjectMocks
    private ThesisService thesisService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateThesisSuccess() {
        UUID thesisId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();
        UUID advisorId = UUID.randomUUID();
        UUID researchLineId = UUID.randomUUID();

        Thesis thesis = new Thesis();
        thesis.setStudentId(studentId);
        thesis.setAdvisorId(advisorId);
        thesis.setTitle("Título de Tesis");
        thesis.setDescription("Descripción de Tesis");
        thesis.setResearchLineId(researchLineId);

        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentId);
        studentDto.setFirstName("Juan");
        studentDto.setLastName("Pérez");
        studentDto.setEmail("juan.perez@example.com");

        AdvisorDto advisorDto = new AdvisorDto();
        advisorDto.setId(advisorId);
        advisorDto.setFirstName("Dr. Ana");
        advisorDto.setLastName("García");
        advisorDto.setEmail("ana.garcia@example.com");

        ResearchLineDto researchLineDto = new ResearchLineDto();
        researchLineDto.setId(researchLineId);
        researchLineDto.setName("Inteligencia Artificial");
        researchLineDto.setDescription("Descripción de la línea de investigación");

        Thesis savedThesis = new Thesis();
        savedThesis.setId(thesisId);
        savedThesis.setStudentId(studentId);
        savedThesis.setAdvisorId(advisorId);
        savedThesis.setTitle("Título de Tesis");
        savedThesis.setDescription("Descripción de Tesis");
        savedThesis.setResearchLineId(researchLineId);
        savedThesis.setCreatedAt(LocalDateTime.now());
        savedThesis.setUpdatedAt(LocalDateTime.now());

        when(studentClient.getStudentById(studentId)).thenReturn(Mono.just(studentDto));
        when(advisorClient.getAdvisorById(advisorId)).thenReturn(Mono.just(advisorDto));
        when(researchLineClient.getResearchLineById(researchLineId)).thenReturn(Mono.just(researchLineDto));
        when(thesisRepository.save(any(Thesis.class))).thenReturn(Mono.just(savedThesis));
        when(streamBridge.send(anyString(), any(ThesisEvent.class))).thenReturn(true);

        Mono<Thesis> result = thesisService.createThesis(thesis);

        StepVerifier.create(result)
                .expectNextMatches(saved -> saved.getId().equals(thesisId) &&
                        saved.getTitle().equals("Título de Tesis"))
                .verifyComplete();

        verify(streamBridge, times(1)).send(anyString(), any(ThesisEvent.class));
    }

    @Test
    void testCreateThesisStudentNotFound() {
        UUID thesisId = UUID.randomUUID();
        UUID studentId = UUID.randomUUID();
        UUID advisorId = UUID.randomUUID();

        Thesis thesis = new Thesis();
        thesis.setStudentId(studentId);
        thesis.setAdvisorId(advisorId);
        thesis.setTitle("Título de Tesis");
        thesis.setDescription("Descripción de Tesis");

        when(studentClient.getStudentById(studentId)).thenReturn(Mono.empty());

        Mono<Thesis> result = thesisService.createThesis(thesis);

        StepVerifier.create(result)
                .expectErrorMessage("El estudiante no existe.")
                .verify();

        verify(streamBridge, times(0)).send(anyString(), any(ThesisEvent.class));
    }

    // Agrega más pruebas según sea necesario
}