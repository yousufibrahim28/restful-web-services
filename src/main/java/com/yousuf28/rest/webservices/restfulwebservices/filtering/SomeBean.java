package com.yousuf28.rest.webservices.restfulwebservices.filtering;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties("field1") // ignores from the response - class level,
// here if the filed name changes we have to make a change in here as well

@JsonFilter("SomeBeanFilter") // Dynamic filtering for the resource
public class SomeBean {
    private String field1;
    //@JsonIgnore // ignores from the response - this preferred, since if filed name changes,
    // we donot have to change in any palace
    private String field2;
    private String field3;
}
