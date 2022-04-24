package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.document.IConsultationApiDocument;
import cl.medical.medicalapp.entity.ConsultationEntity;
import cl.medical.medicalapp.model.ConsultationResumeModel;
import cl.medical.medicalapp.service.IConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("consultation")
public class ConsultationController implements IConsultationApiDocument {

    private final IConsultationService consultationService;

    @Autowired
    public ConsultationController(IConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ConsultationEntity>> findAll() {
        List<ConsultationEntity> consultations = this.consultationService.findAll();
        return ResponseEntity.ok(consultations);
    }

    @Override
    @GetMapping(value = "resume", produces = {"application/hal+json"})
    public ResponseEntity<CollectionModel<ConsultationResumeModel>> findAllConsultationResume() {
        CollectionModel<ConsultationResumeModel> consultationResumeModels = this.consultationService.findAllConsultationResume();
        return ResponseEntity.ok(consultationResumeModels);
    }

    @Override
    @GetMapping(value = "{id}/resume", produces = {"application/hal+json"})
    public ResponseEntity<ConsultationResumeModel> findByIdConsultationResume(@PathVariable("id") Integer id) {
        ConsultationResumeModel consultationResumeModel = this.consultationService.findByIdConsultationResume(id);
        return ResponseEntity.ok(consultationResumeModel);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ConsultationEntity> findById(@PathVariable("id") Integer id) {
        ConsultationEntity consultation = this.consultationService.findById(id);
        return ResponseEntity.ok(consultation);
    }

    @Override
    @PostMapping
    public ResponseEntity<ConsultationEntity> save(@Valid @RequestBody ConsultationEntity consultation) {
        ConsultationEntity consultationSaved = this.consultationService.save(consultation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultationSaved.getIdConsultation()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ConsultationEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody ConsultationEntity consultation) {
        ConsultationEntity consultationUpdated = this.consultationService.update(id, consultation);
        return ResponseEntity.ok(consultationUpdated);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ConsultationEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
