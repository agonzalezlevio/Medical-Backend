package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.entity.DoctorEntity;
import cl.medical.medicalapp.service.IDoctorService;
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
@RequestMapping("doctor")
@Tag(name = "doctor")
public class DoctorController {

    private final IDoctorService doctorService;

    @Autowired
    public DoctorController(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Operation(summary = "Find all doctors", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DoctorEntity.class)))),
    })
    @GetMapping
    public ResponseEntity<List<DoctorEntity>> findAll() {
        List<DoctorEntity> doctors = this.doctorService.findAll();
        return ResponseEntity.ok(doctors);
    }

    @Operation(summary = "Find doctor by ID", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = DoctorEntity.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<DoctorEntity> findById(@PathVariable("id") Integer id) {
        DoctorEntity doctor = this.doctorService.findById(id);
        return ResponseEntity.ok(doctor);
    }

    @Operation(summary = "Add a new doctor", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor created", content = @Content(schema = @Schema(implementation = DoctorEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Doctor or fields already exists")
    })
    @PostMapping
    public ResponseEntity<DoctorEntity> save(@Valid @RequestBody DoctorEntity doctor) {
        DoctorEntity doctorSaved = this.doctorService.save(doctor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(doctorSaved.getIdDoctor()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing doctor", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<DoctorEntity> update(@PathVariable("id") Integer id, @Valid @RequestBody DoctorEntity doctor) {
        DoctorEntity doctorUpdated = this.doctorService.update(id, doctor);
        return ResponseEntity.ok(doctorUpdated);
    }

    @Operation(summary = "Deletes a doctor", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<DoctorEntity> delete(@Valid @PathVariable("id") Integer id) {
        this.doctorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
