package it.si2001.webapp.springbootapplication.service;

import it.si2001.webapp.springbootapplication.model.Typology;
import it.si2001.webapp.springbootapplication.repository.TypologyRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TypologyService {

    private final TypologyRepository typologyRepository;
    public TypologyService(TypologyRepository typologyRepository) { this.typologyRepository = typologyRepository; }

    @Transactional
    public List<Typology> getAll(){
        return typologyRepository.findAll();
    }
    @Transactional
    public Optional<Typology> get(int id) throws ResourceNotFoundException { return typologyRepository.findById(id);}
    @Transactional
    public Typology getType(String type) {
        return typologyRepository.findByType(type);
    }
    @Transactional
    public void delete(int id){
        typologyRepository.deleteById(id);
    }
    @Transactional
    public void save(Typology typology){
        typologyRepository.saveAndFlush(typology);
    }

}
