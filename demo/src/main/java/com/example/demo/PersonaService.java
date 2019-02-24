package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Iterable<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }

    public Persona getPersona(Long id){
        return personaRepository.findById(id).orElse(null);
    }

    public boolean addPersona(Persona persona){
        if(persona.getNombre() == null || persona.getApellido() == null) return false;
        else{
            personaRepository.save(persona);
            return true;
        }
    }

    public boolean updatePersona(Long id, Persona persona){
        if(personaRepository.existsById(id)){
            Persona personaToUpdate = personaRepository.findById(id).get();
            if(persona.getNombre() != null){
                personaToUpdate.setNombre(persona.getNombre());
            }
            if(persona.getApellido() != null){
                personaToUpdate.setApellido(persona.getApellido());
            }
            personaRepository.save(personaToUpdate);
            return true;
        }
        else return false;
    }

    public boolean removePersona(Long id){
        if(personaRepository.existsById(id)){
            personaRepository.deleteById(id);
            return true;
        }
        else return false;
    }


}
