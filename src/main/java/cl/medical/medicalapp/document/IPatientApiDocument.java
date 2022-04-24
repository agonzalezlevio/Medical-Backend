package cl.medical.medicalapp.document;

import cl.medical.medicalapp.entity.PatientEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "patient")
public interface IPatientApiDocument {

    @Operation(summary = "Find all patients", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PatientEntity.class)))),
    })
    ResponseEntity<List<PatientEntity>> findAll();

    @Operation(summary = "Find patient by ID", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = PatientEntity.class))),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    ResponseEntity<PatientEntity> findById(Integer id);

    @Operation(summary = "Add a new patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Patient created", content = @Content(schema = @Schema(implementation = PatientEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Patient or fields already exists")
    })
    ResponseEntity<PatientEntity> save(PatientEntity patient);

    @Operation(summary = "Update an existing patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Patient not found"),
    })
    ResponseEntity<PatientEntity> update(Integer id, PatientEntity patient);

    @Operation(summary = "Deletes a patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Patient not found")})
    ResponseEntity<PatientEntity> delete(Integer id);

}
