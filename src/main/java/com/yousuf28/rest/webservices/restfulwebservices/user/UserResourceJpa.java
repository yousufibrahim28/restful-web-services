package com.yousuf28.rest.webservices.restfulwebservices.user;

import com.yousuf28.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.yousuf28.rest.webservices.restfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RestController
@AllArgsConstructor
public class UserResourceJpa {
    private UserRepository repository;

    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }
    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){

        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<User> entityModelUser = EntityModel.of(user.get());

        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModelUser.add(link.withRel("all-users"));
        return entityModelUser;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);
        // Creating a link back to the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping (path = "/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        return user.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        post.setUser(user.get());
        Post savedPost = postRepository.save(post);
        // Creating a link back to the created user
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{postId}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping (path = "/jpa/users/{id}/posts/{postId}")
    public void deletePost(@PathVariable Integer id,@PathVariable Integer postId){
        postRepository.deleteById(postId);
    }

    @GetMapping (path = "/jpa/users/{id}/posts/{postId}")
    public Post retrieveOnePostOfUser(@PathVariable Integer id,@PathVariable Integer postId){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("id: " + id);
        }
        Predicate<? super Post> predicate = post -> post.getId().equals(postId);
        return user.get().getPosts().stream().filter(predicate).findFirst().orElse(null);
    }
}
