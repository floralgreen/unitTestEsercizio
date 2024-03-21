package esercizidevelhope.unitTestEsercizio.controllers;


import esercizidevelhope.unitTestEsercizio.entities.Utente;
import esercizidevelhope.unitTestEsercizio.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService userService;

    @PostMapping("/add")
    public ResponseEntity<Utente> addUser(@RequestBody Utente user){
        userService.createUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Utente>> getAllUser(){
        List<Utente> allUsers =   userService.getAllUser();
        return ResponseEntity.ok().body(allUsers);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Utente>> getUser(@PathVariable Long id){
        Optional<Utente> userOptional = userService.getUser(id);
        return ResponseEntity.ok().body(userOptional);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Utente> updateUserById(@RequestBody Utente user,@PathVariable Long id){
        Optional<Utente> userOptional = userService.updateUser(id,user);
        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Utente> deleteUser(@PathVariable Long id){
        Optional<Utente> userDaCancellare = userService.deleteUser(id);
        return ResponseEntity.ok().body(userDaCancellare.get());
    }




}