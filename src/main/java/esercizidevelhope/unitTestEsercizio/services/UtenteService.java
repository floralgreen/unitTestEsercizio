package esercizidevelhope.unitTestEsercizio.services;


import esercizidevelhope.unitTestEsercizio.entities.Utente;
import esercizidevelhope.unitTestEsercizio.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository userRepository;

    public Utente createUser(Utente user) {
        Utente savedUser = userRepository.save(user);
        return savedUser;
    }

    public List<Utente> getAllUser() {
        List<Utente> users = (List<Utente>) userRepository.findAll();
        return users;
    }

    public Optional<Utente> getUser(Long id) {
        Optional<Utente> userOptional = userRepository.findById(id);
        return userOptional;
    }

    public Optional<Utente> updateUser(Long id,Utente user){
        Optional<Utente> userDaAggiornare = userRepository.findById(id);
        if (userDaAggiornare.isPresent()){
            userDaAggiornare.get().setName(user.getName());
            userDaAggiornare.get().setSurname(user.getSurname());
            userRepository.save(userDaAggiornare.get());
        } else {
            return Optional.empty();
        }
        return userDaAggiornare;
    }


    public Optional<Utente> deleteUser(Long id){
        Optional<Utente> utenteOptional = userRepository.findById(id);
        if (utenteOptional.isPresent()){
            userRepository.deleteById(id);
        }
        return utenteOptional;
    }
}
