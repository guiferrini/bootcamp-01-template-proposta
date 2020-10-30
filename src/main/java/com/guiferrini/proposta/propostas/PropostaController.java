package com.guiferrini.proposta.propostas;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private ValidadorPropostaDuplicada validadorPropostaDuplicada;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@Valid @RequestBody PropostaRequest propostaRequest,
                                         UriComponentsBuilder builder){

        if(!validadorPropostaDuplicada.validadoDocumento(propostaRequest)){
            return ResponseEntity.status(422).body("ERRO. Já existe uma Porposta com esse Documento.");
        }

        Proposta obj = propostaRequest.toModel();
        entityManager.persist(obj);

        //URI uri = builder.path("/propostas/{id}").build(obj.getId());
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        if(obj instanceof Proposta){
            return ResponseEntity.created(builder.path("/propostas/{id}").buildAndExpand(obj.getId()).toUri()).build();
        } else {
            //return ResponseEntity.status(400).body(null);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
