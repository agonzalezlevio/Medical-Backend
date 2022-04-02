package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.dto.ConsultationResumeDto;
import cl.medical.medicalapp.model.Consultation;
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
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Consultation.class)))),
    })
    @GetMapping
    public ResponseEntity<List<Consultation>> findAll() {
        List<Consultation> consultationList = this.consultationService.findAll();
        return ResponseEntity.ok(consultationList);
    }

    @Operation(summary = "Find all consultations resume", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/hal+json", array = @ArraySchema(schema = @Schema(implementation = ConsultationResumeDto.class)))),
    })
    @GetMapping(value = "resume", produces = { "application/hal+json" })
    public ResponseEntity<CollectionModel<ConsultationResumeDto>> findAllConsultationResume() {
        CollectionModel<ConsultationResumeDto> ConsultationResumeDtoList = this.consultationService.findAllConsultationResume();
        return ResponseEntity.ok(ConsultationResumeDtoList);
    }

    @Operation(summary = "Find consultation resume by ID", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/hal+json", schema = @Schema(implementation = ConsultationResumeDto.class))),
            @ApiResponse(responseCode = "404", description = "Consultation not found")
    })
    @GetMapping(value = "{id}/resume", produces = { "application/hal+json" })
    public ResponseEntity<ConsultationResumeDto> findByIdConsultationResume(@PathVariable("id") Integer id) {
        ConsultationResumeDto ConsultationResumeDto = this.consultationService.findByIdConsultationResume(id);
        return ResponseEntity.ok(ConsultationResumeDto);
    }

    @Operation(summary = "Find consultation by ID", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Consultation.class))),
            @ApiResponse(responseCode = "404", description = "Consultation not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<Consultation> findById(@PathVariable("id") Integer id) {
        Consultation consultation = this.consultationService.findById(id);
        return ResponseEntity.ok(consultation);
    }



    @Operation(summary = "Add a new consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consultation created", content = @Content(schema = @Schema(implementation = Consultation.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Consultation or fields already exists")
    })
    @PostMapping
    public ResponseEntity<Consultation> save(@Valid @RequestBody Consultation consultation) {
        Consultation consultationSaved = this.consultationService.save(consultation);
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
    public ResponseEntity<Consultation> update(@PathVariable("id") Integer id, @Valid @RequestBody Consultation consultation) {
        Consultation consultationUpdated = this.consultationService.update(id, consultation);
        return ResponseEntity.ok(consultationUpdated);
    }

    @Operation(summary = "Deletes a consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Consultation not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<Consultation> delete(@Valid @PathVariable("id") Integer id) {
        this.consultationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
