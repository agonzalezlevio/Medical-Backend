package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.document.IExaminationApiDocument;
import cl.medical.medicalapp.entity.ExaminationEntity;
import cl.medical.medicalapp.service.IExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("examination")
public class ExaminationController implements IExaminationApiDocument {

    private final IExaminationService examinationService;

    @Autowired
    public ExaminationController(IExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ExaminationEntity>> findAll() {
        List<ExaminationEntity> examinations = this.examinationService.findAll();
        return ResponseEntity.ok(examinations);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<ExaminationEntity> findById(@PathVariable("id") Integer id) {
        ExaminationEntity examination = this.examinationService.findById(id);
        return ResponseEntity.ok(examination);
    }

    @Override
    @PostMapping
    public ResponseEntity<ExaminationEntity> save(@Valid @RequestBody ExaminationEntity examination) {
        ExaminationEntity examinationSaved = this.examinationService.save(examination);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(examinationSaved.getIdExamination()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<ExaminationEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody ExaminationEntity examination) {
        ExaminationEntity examinationUpdated = this.examinationService.update(id, examination);
        return ResponseEntity.ok(examinationUpdated);
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<ExaminationEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.examinationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
