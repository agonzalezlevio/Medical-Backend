package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.model.Examination;
import cl.medical.medicalapp.service.IExaminationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "examination")
public class ExaminationController {

    private final IExaminationService examinationService;

    @Autowired
    public ExaminationController(IExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @Operation(summary = "Find all examinations", tags = {"examinations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Examination.class)))),
    })
    @GetMapping
    public ResponseEntity<List<Examination>> findAll() {
        List<Examination> examinationList = this.examinationService.findAll();
        return ResponseEntity.ok(examinationList);
    }

    @Operation(summary = "Find examination by ID", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Examination.class))),
            @ApiResponse(responseCode = "404", description = "Examination not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<Examination> findById(@PathVariable("id") Integer id) {
        Examination examination = this.examinationService.findById(id);
        return ResponseEntity.ok(examination);
    }

    @Operation(summary = "Add a new examination", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Examination created", content = @Content(schema = @Schema(implementation = Examination.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Examination or fields already exists")
    })
    @PostMapping
    public ResponseEntity<Examination> save(@Valid @RequestBody Examination examination) {
        Examination examinationSaved = this.examinationService.save(examination);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(examinationSaved.getIdExamination()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing examination", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Examination not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<Examination> update(@PathVariable("id") Integer id, @Valid @RequestBody Examination examination) {
        Examination examinationUpdated = this.examinationService.update(id, examination);
        return ResponseEntity.ok(examinationUpdated);
    }

    @Operation(summary = "Deletes a examination", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Examination not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<Examination> delete(@Valid @PathVariable("id") Integer id) {
        this.examinationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
