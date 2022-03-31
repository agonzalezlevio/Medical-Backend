package cl.medical.medicalapp.controller;


import cl.medical.medicalapp.model.UserAccount;
import cl.medical.medicalapp.service.IUserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-account")
@Tag(name = "user-account")
public class UserAccountController {

    private final IUserAccountService userAccountService;

    @Autowired
    public UserAccountController(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operation(summary = "Find all user accounts", tags = {"user-account"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserAccount.class)))),
    })
    @GetMapping
    public ResponseEntity<List<UserAccount>> findAll() {
        List<UserAccount> userAccountList = this.userAccountService.findAll();
        return ResponseEntity.ok(userAccountList);
    }

}
