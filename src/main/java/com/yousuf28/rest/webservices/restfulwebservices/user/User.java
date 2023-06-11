package com.yousuf28.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "Name should have at least 2 characters")
    @JsonProperty("user_name") // Customizing field name in response
    private String name;

    @Past(message = "Birth date should be in the Past")
    @JsonProperty("DOB") // Customizing field name in response
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user") // Tells the relationship
    @JsonIgnore
    private List<Post> posts;
}
