package cl.medical.medicalapp.document;

import cl.medical.medicalapp.entity.DoctorEntity;
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

@Tag(name = "doctor")
public interface IDoctorApiDocument {

    @Operation(summary = "Find all doctors", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = DoctorEntity.class)))),
    })
    ResponseEntity<List<DoctorEntity>> findAll();

    @Operation(summary = "Find doctor by ID", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = DoctorEntity.class))),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    ResponseEntity<DoctorEntity> findById(Integer id);

    @Operation(summary = "Add a new doctor", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor created", content = @Content(schema = @Schema(implementation = DoctorEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Doctor or fields already exists")
    })
    ResponseEntity<DoctorEntity> save(DoctorEntity doctor);

    @Operation(summary = "Update an existing doctor", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Doctor not found"),
    })
    ResponseEntity<DoctorEntity> update(Integer id, DoctorEntity doctor);

    @Operation(summary = "Deletes a doctor", tags = {"doctor"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")})
    ResponseEntity<DoctorEntity> delete(Integer id);

}
