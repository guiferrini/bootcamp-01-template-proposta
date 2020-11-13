package com.guiferrini.proposta.biometria;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("cartoes")
public class BiometriaController {

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @PostMapping("/{idCartao}/biometrias")
    public ResponseEntity<?> criar(@PathVariable String idCartao,
                                   @Valid @RequestBody BiometriaRequest biometriaRequest,
                                    UriComponentsBuilder builder){

        logger.info("Inicio Cadastro Biometria");

        Cartao cartao = entityManager.find(Cartao.class, idCartao);

        if(cartao == null){
            logger.error("Não localizado o ID Cartão digitado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO. Favor validar ID do Cartão."); //404
        }
        logger.info("Cadastro Biometria para Cartoa ID {}", cartao.getId());

        Biometria biometria = biometriaRequest.toModel();
        entityManager.persist(biometria);
        logger.info("Biometria cadastrada - Biometria ID {}", biometria.getId());

        cartao.adicionaBiometria(biometria);
        entityManager.persist(cartao);
        logger.info("Adicionada Biometria ao Cartao");

        if(biometria instanceof Biometria){
            logger.info("Finalizado com Sucesso Cadastro da Biometria no cartão");
            return ResponseEntity.created(builder.path("/biometrias/{id}").buildAndExpand(cartao.getId()).toUri()).build(); //201

        }
        else {
            logger.error("Erro ao Cadastrar Biometria no Cartão");
            return ResponseEntity.badRequest().body(null); //400
        }

    }

}
