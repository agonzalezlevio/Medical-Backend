package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.model.Patient;
import cl.medical.medicalapp.service.IPatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("patient")
@Tag(name = "patient")
public class PatientController {

    private final IPatientService patientService;

    @Autowired
    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(summary = "Find all patients", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Patient.class)))),
    })
    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patientList = this.patientService.findAll();
        return ResponseEntity.ok(patientList);
    }

    @Operation(summary = "Find patient by ID", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id) {
        Patient patient = this.patientService.findById(id);
        return ResponseEntity.ok(patient);
    }

    @Operation(summary = "Add a new patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Patient or fields already exists")
    })
    @PostMapping
    public ResponseEntity<Patient> save(@Valid @RequestBody Patient patient) {
        Patient patientSaved = this.patientService.save(patient);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patientSaved.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<Patient> update(@PathVariable("id") Integer id, @Valid @RequestBody Patient patient) {
        Patient patientUpdated = this.patientService.update(id, patient);
        return ResponseEntity.ok(patientUpdated);
    }

    @Operation(summary = "Deletes a patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Patient not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<Patient> delete(@Valid @PathVariable("id") Integer id) {
        this.patientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
