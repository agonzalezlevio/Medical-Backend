package cl.medical.medicalapp.document;

import cl.medical.medicalapp.entity.SpecialtyEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "specialty")
public interface ISpecialtyApiDocument {

    @Operation(summary = "Find all specialties", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SpecialtyEntity.class)))),
    })
    ResponseEntity<List<SpecialtyEntity>> findAll();

    @Operation(summary = "Find specialty by ID", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = SpecialtyEntity.class))),
            @ApiResponse(responseCode = "404", description = "Specialty not found")
    })
    ResponseEntity<SpecialtyEntity> findById(Integer id);

    @Operation(summary = "Add a new specialty", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Specialty created", content = @Content(schema = @Schema(implementation = SpecialtyEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Specialty or fields already exists")
    })
    ResponseEntity<SpecialtyEntity> save(SpecialtyEntity specialty);

    @Operation(summary = "Update an existing specialty", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Specialty not found"),
    })
    ResponseEntity<SpecialtyEntity> update(Integer id, SpecialtyEntity specialty);

    @Operation(summary = "Deletes a specialty", tags = {"specialty"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Specialty not found")})
    ResponseEntity<SpecialtyEntity> delete(Integer id);
}
