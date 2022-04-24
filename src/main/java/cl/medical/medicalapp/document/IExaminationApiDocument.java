package cl.medical.medicalapp.document;

import cl.medical.medicalapp.entity.ExaminationEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "examination")
public interface IExaminationApiDocument {

    @Operation(summary = "Find all examinations", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ExaminationEntity.class)))),
    })
    ResponseEntity<List<ExaminationEntity>> findAll();

    @Operation(summary = "Find examination by ID", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ExaminationEntity.class))),
            @ApiResponse(responseCode = "404", description = "Examination not found")
    })
    ResponseEntity<ExaminationEntity> findById(Integer id);

    @Operation(summary = "Add a new examination", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Examination created", content = @Content(schema = @Schema(implementation = ExaminationEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Examination or fields already exists")
    })
    ResponseEntity<ExaminationEntity> save(ExaminationEntity examination);

    @Operation(summary = "Update an existing examination", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Examination not found"),
    })
    ResponseEntity<ExaminationEntity> update(Integer id, ExaminationEntity examination);

    @Operation(summary = "Deletes a examination", tags = {"examination"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Examination not found")})
    ResponseEntity<ExaminationEntity> delete(Integer id);

}
