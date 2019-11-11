package it.si2001.webapp.springbootapplication.controller;

import it.si2001.webapp.springbootapplication.dto.RegisterUserDTO;
import it.si2001.webapp.springbootapplication.dto.RegisterUserMapper;
import it.si2001.webapp.springbootapplication.dto.UserDTO;
import it.si2001.webapp.springbootapplication.dto.UserMapper;
import it.si2001.webapp.springbootapplication.model.Typology;
import it.si2001.webapp.springbootapplication.model.User;
import it.si2001.webapp.springbootapplication.service.TypologyService;
import it.si2001.webapp.springbootapplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Slf4j

@RestController
@RequestMapping( value = "api/user")
public class UserController {

    private final UserService userService;
    private final TypologyService typologyService;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;


    public UserController(UserService userService, TypologyService typologyService, UserMapper userMapper, PasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.typologyService = typologyService;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(this.userMapper.convertToListDTO(this.userService.getUsers()));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> findById(@PathVariable int id) {
        Optional<User> user = this.userService.getUser(id);
        return ResponseEntity.ok(this.userMapper.convertToDTO(user.orElse(null)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        Optional<Typology> typology = this.typologyService.get(userDTO.getTypologyId());

        if (typology.isPresent()){
            User user = this.userMapper.convertToModel(userDTO);
            user.setTypology(typology.get());
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            this.userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userDTO);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> update(@PathVariable int id, @RequestBody UserDTO userDTO) {
        Optional<User> user = this.userService.getUser(id);
        if(user.isPresent()) {
            user.get().setName(userDTO.getName());
            user.get().setEmail(userDTO.getEmail());
            user.get().setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            this.userService.saveUser(user.get());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDTO);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<User> user = this.userService.getUser(id);
        if(user.isPresent()) {
            this.userService.deleteUser(user.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found user with " + id + " ID");
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody RegisterUserDTO userDTO) throws ParseException {
        String type = (userDTO.isAdmin())?"Admin":"Customer";
        Typology typology = this.typologyService.getType(type);
        User user = RegisterUserMapper.convertToModel(userDTO, typology);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }
}
