package at.froehlich.demo.web;

import at.froehlich.demo.data.JDBCConnection;
import at.froehlich.demo.data.PersonRepository;
import at.froehlich.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(path = "/person")
    public List<Person> getPersonWithLastName(@RequestParam String name, @RequestParam String safe){
        if (safe.equals("on")){
            return personRepository.getPersonsWithLastName(name);
        }else{
            return JDBCConnection.getPersonsWithLastName(name);
        }
    }

}
