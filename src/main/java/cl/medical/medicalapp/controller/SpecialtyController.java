package cl.medical.medicalapp.controller;

import cl.medical.medicalapp.model.Specialty;
import cl.medical.medicalapp.service.ISpecialtyService;
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
@RequestMapping("specialty")
@Tag(name = "specialty")
public class SpecialtyController {

    private final ISpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(ISpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Operation(summary = "Find all specialties", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Specialty.class)))),
    })
    @GetMapping
    public ResponseEntity<List<Specialty>> findAll() {
        List<Specialty> specialtyList = this.specialtyService.findAll();
        return ResponseEntity.ok(specialtyList);
    }

    @Operation(summary = "Find specialty by ID", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = Specialty.class))),
            @ApiResponse(responseCode = "404", description = "Specialty not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<Specialty> findById(@PathVariable("id") Integer id) {
        Specialty specialty = this.specialtyService.findById(id);
        return ResponseEntity.ok(specialty);
    }

    @Operation(summary = "Add a new specialty", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Specialty created", content = @Content(schema = @Schema(implementation = Specialty.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Specialty or fields already exists")
    })
    @PostMapping
    public ResponseEntity<Specialty> save(@Valid @RequestBody Specialty specialty) {
        Specialty specialtySaved = this.specialtyService.save(specialty);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(specialtySaved.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update an existing specialty", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Specialty not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<Specialty> update(@PathVariable("id") Integer id, @Valid @RequestBody Specialty specialty) {
        Specialty specialtyUpdated = this.specialtyService.update(id, specialty);
        return ResponseEntity.ok(specialtyUpdated);
    }

    @Operation(summary = "Deletes a specialty", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Specialty not found")})
    @DeleteMapping("{id}")
    public ResponseEntity<Specialty> delete(@Valid @PathVariable("id") Integer id) {
        this.specialtyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
