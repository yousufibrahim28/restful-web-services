package com.yousuf28.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserResource {

    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }
    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){

        User user = service.findOne(id);
        if(user==null) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> entityModelUser = EntityModel.of(user);

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModelUser.add(link.withRel("all-users"));
        return entityModelUser;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        // Creating a link back to the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (path = "/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        service.deleteById(id);
    }
}
