package com.yousuf28.rest.webservices.restfulwebservices.filtering;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("field1", "field2", "field3");
        List<String> fieldList = Arrays.asList("field1","field3");
        return filterResourceFields(Arrays.asList(someBean),fieldsToBeIncluded(fieldList));
    }

    @GetMapping("/filtering/list")
    public MappingJacksonValue filteringList() {
        List<SomeBean> someBeanList = Arrays.asList(new  SomeBean("field1", "field2", "field3"),
                new SomeBean("field4", "field5", "field6"));
        List<String> fieldList = Arrays.asList("field2","field3");
        return filterResourceFields(someBeanList,fieldsToBeIncluded(fieldList));
    }

    private MappingJacksonValue filterResourceFields(List<SomeBean> someBeanList, Set<String> fields) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBeanList);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    private Set<String> fieldsToBeIncluded(List<String> fields) {
        Set<String> fieldsToBeIncluded = new HashSet<>();
        for (String field: fields) {
            fieldsToBeIncluded.add(field);
        }
        return fieldsToBeIncluded;
    }
}
