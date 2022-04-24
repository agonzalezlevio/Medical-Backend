package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.entity.ConsultationEntity;
import cl.medical.medicalapp.model.ConsultationResumeModel;
import cl.medical.medicalapp.service.IConsultationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "consultation")
public class ConsultationController {

    private final IConsultationService consultationService;

    @Autowired
    public ConsultationController(IConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @Operation(summary = "Find all consultations", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ConsultationEntity.class)))),
    })
    @GetMapping
    public ResponseEntity<List<ConsultationEntity>> findAll() {
        List<ConsultationEntity> consultations = this.consultationService.findAll();
        return ResponseEntity.ok(consultations);
    }

    @Operation(summary = "Find all consultations resume", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/hal+json", array = @ArraySchema(schema = @Schema(implementation = ConsultationResumeModel.class)))),
    })
    @GetMapping(value = "resume", produces = {"application/hal+json"})
    public ResponseEntity<CollectionModel<ConsultationResumeModel>> findAllConsultationResume() {
        CollectionModel<ConsultationResumeModel> consultationResumeModels = this.consultationService.findAllConsultationResume();
        return ResponseEntity.ok(consultationResumeModels);
    }

    @Operation(summary = "Find consultation resume by ID", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/hal+json", schema = @Schema(implementation = ConsultationResumeModel.class))),
            @ApiResponse(responseCode = "404", description = "Consultation not found")
    })
    @GetMapping(value = "{id}/resume", produces = {"application/hal+json"})
    public ResponseEntity<ConsultationResumeModel> findByIdConsultationResume(@PathVariable("id") Integer id) {
        ConsultationResumeModel consultationResumeModel = this.consultationService.findByIdConsultationResume(id);
        return ResponseEntity.ok(consultationResumeModel);
    }

    @Operation(summary = "Find consultation by ID", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ConsultationEntity.class))),
            @ApiResponse(responseCode = "404", description = "Consultation not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<ConsultationEntity> findById(@PathVariable("id") Integer id) {
        ConsultationEntity consultation = this.consultationService.findById(id);
        return ResponseEntity.ok(consultation);
    }

    @Operation(summary = "Add a new consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consultation created", content = @Content(schema = @Schema(implementation = ConsultationEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Consultation or fields already exists")
    })
    @PostMapping
    public ResponseEntity<ConsultationEntity> save(@Valid @RequestBody ConsultationEntity consultation) {
        ConsultationEntity consultationSaved = this.consultationService.save(consultation);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(consultationSaved.getIdConsultation()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Consultation not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<ConsultationEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody ConsultationEntity consultation) {
        ConsultationEntity consultationUpdated = this.consultationService.update(id, consultation);
        return ResponseEntity.ok(consultationUpdated);
    }

    @Operation(summary = "Deletes a consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Consultation not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<ConsultationEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
