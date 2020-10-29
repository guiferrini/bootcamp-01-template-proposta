package com.guiferrini.proposta.propostas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    @PersistenceContext
    EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<Proposta> cria(@Valid @RequestBody PropostaRequest propostaRequest,
                                         UriComponentsBuilder builder){

        Proposta obj = propostaRequest.toModel();
        entityManager.persist(obj);

        //URI uri = builder.path("/propostas/{id}").build(obj.getId());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        if(obj instanceof Proposta){
            return ResponseEntity.created(builder.path("/propostas/{id}").buildAndExpand(obj.getId()).toUri()).build();
        } else {
            return ResponseEntity.status(400).body(null);
        }

        //return ResponseEntity.created(uri).build();
    }
}
