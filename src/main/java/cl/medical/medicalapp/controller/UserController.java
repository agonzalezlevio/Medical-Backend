package cl.medical.medicalapp.controller;


import cl.medical.medicalapp.document.IUserApiDocument;
import cl.medical.medicalapp.entity.UserEntity;
import cl.medical.medicalapp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user-account")
public class UserController implements IUserApiDocument {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }

}
