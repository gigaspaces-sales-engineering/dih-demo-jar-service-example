package com.example.demo.controller;

import com.example.demo.model.Person;
import com.gigaspaces.client.WriteModifiers;
import org.openspaces.core.GigaSpace;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class PersonController {

    private final GigaSpace gigaSpace;

    public PersonController(GigaSpace gigaSpace) {
        this.gigaSpace = gigaSpace;
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Person createPerson(@RequestBody Person person) {
        String id = gigaSpace.write(person, WriteModifiers.WRITE_ONLY).getUID();
        return getPersonById(id);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public Person updatePerson(@RequestBody Person person, @PathVariable String id) {
        person.setId(id);
        gigaSpace.write(person, WriteModifiers.UPDATE_ONLY);
        return getPersonById(id);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable String id) {
        return getPersonById(id);
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public List<Person> getPersons() {
        return Arrays.asList(gigaSpace.readMultiple(new Person()));
    }

    private Person getPersonById(String id) {
        return gigaSpace.readById(Person.class, id);
    }
}