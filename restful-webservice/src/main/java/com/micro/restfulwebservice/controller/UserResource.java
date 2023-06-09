package com.micro.restfulwebservice.controller;

import com.micro.restfulwebservice.dto.User;
import com.micro.restfulwebservice.service.UserDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null) {
            throw new RuntimeException("not found");
        }
        //"all-users - > SERVER_PATH+"/users"
        //Get Method url with hateos
//      user.add(Link.of)

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User newUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/hello-world-internationalization")
    public String helloInternatilazation(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message ", null, locale);
    }

}
