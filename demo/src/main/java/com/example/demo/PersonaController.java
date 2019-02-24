package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping(produces = "application/json")
    public ResponseEntity getAllPersonas(){
        return new ResponseEntity(personaService.getAllPersonas(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity getPersona(@PathVariable Long id){
        Persona persona = personaService.getPersona(id);
        if(persona != null) return new ResponseEntity(persona, HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity savePersona(@RequestBody Persona persona){
        if(personaService.addPersona(persona)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "{id}", produces = "application/json")
    public ResponseEntity updatePersona(@PathVariable Long id, @RequestBody Persona persona){
        if(personaService.updatePersona(id, persona)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "{id}", produces = "application/json")
    public ResponseEntity removePersona(@PathVariable Long id){
        if(personaService.removePersona(id)) return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
