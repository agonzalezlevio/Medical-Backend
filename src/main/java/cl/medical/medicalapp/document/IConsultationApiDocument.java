package cl.medical.medicalapp.document;

import cl.medical.medicalapp.entity.ConsultationEntity;
import cl.medical.medicalapp.model.ConsultationResumeModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "consultation")
public interface IConsultationApiDocument {

    @Operation(summary = "Find all consultations", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ConsultationEntity.class)))),
    })
    ResponseEntity<List<ConsultationEntity>> findAll();

    @Operation(summary = "Find all consultations resume", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ConsultationResumeModel.class)))),
    })
    ResponseEntity<CollectionModel<ConsultationResumeModel>> findAllConsultationResume();

    @Operation(summary = "Find consultation resume by ID", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = MediaTypes.HAL_JSON_VALUE, schema = @Schema(implementation = ConsultationResumeModel.class))),
            @ApiResponse(responseCode = "404", description = "Consultation not found")
    })
    ResponseEntity<ConsultationResumeModel> findByIdConsultationResume(Integer id);

    @Operation(summary = "Find consultation by ID", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ConsultationEntity.class))),
            @ApiResponse(responseCode = "404", description = "Consultation not found")
    })
    ResponseEntity<ConsultationEntity> findById(Integer id);

    @Operation(summary = "Add a new consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Consultation created", content = @Content(schema = @Schema(implementation = ConsultationEntity.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Consultation or fields already exists")
    })
    ResponseEntity<ConsultationEntity> save(ConsultationEntity consultation);

    @Operation(summary = "Update an existing consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Consultation not found"),
    })
    ResponseEntity<ConsultationEntity> update(Integer id, ConsultationEntity consultation);

    @Operation(summary = "Deletes a consultation", tags = {"consultation"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Consultation not found")})
    ResponseEntity<ConsultationEntity> delete(Integer id);
}
