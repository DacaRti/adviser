package com.spring.program.project.controller;

import com.spring.program.project.config.jwt.JwtUtils;
import com.spring.program.project.entity.Role;
import com.spring.program.project.entity.User;
import com.spring.program.project.pojo.JwtResponse;
import com.spring.program.project.pojo.LoginRequest;
import com.spring.program.project.services.RoleService;
import com.spring.program.project.services.UserService;
import com.spring.program.project.utils.ConstantUtils;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author DacaP
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
@CrossOrigin()
public class UserRestController {

    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final RoleService roleService;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserRestController(AuthenticationManager authenticationManager,
            UserService userService,
            PasswordEncoder passwordEncoder,
            RoleService roleService,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody User user) {
        log.info("UserResourceImpl : register");
        JSONObject jsonObject = new JSONObject();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName(ConstantUtils.USER.toString().toLowerCase()));
        User savedUser = userService.saveAndFlush(user);
        jsonObject.put("message", savedUser.getUsername() + " saved successfully");
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                roles));
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.remove(id);
        return "User with id = " + id + " was deleted";
    }

    @GetMapping("/roles")
    public List<Role> getRoles() {
        return roleService.findAll();
    }
}
