package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.services.PropostaServices;
import com.guiferrini.proposta.servicoWeb.OperacaoServicoWebFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
@RequestMapping("/propostas")
public class ConsultaPropostaController {

    private final Logger logger = LoggerFactory.getLogger(CadastraPropostaController.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ValidadorPropostaDuplicada validadorPropostaDuplicada;

    @Autowired
    private OperacaoServicoWebFeign operacaoServicoWebFeign;

    @Autowired
    private PropostaServices propostaServices;

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> busca(@PathVariable("id") String id){

        logger.info("Buscando Proposta pelo ID {}", id);
        Proposta obj = entityManager.find(Proposta.class, id);

        //alternativa p n usar 'null' -> if(obj.getId().isEmpty()){...}
        if(obj == null){
            logger.error("ERRO. Não existe Proposta para o ID {}", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO. Proposta não localizada."); //500
        }

        PropostaResponse propostaResponse = new PropostaResponse(obj);

        logger.info("Proposta {} Localizada com Sucesso.", id);
        return ResponseEntity.status(HttpStatus.OK).body(propostaResponse); //200

    }

}
