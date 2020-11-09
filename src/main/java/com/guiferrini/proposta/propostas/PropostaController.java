package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.services.PropostaServices;
import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.OperacaoServicoWebFeign;
import com.guiferrini.proposta.servicoWeb.Request.SolicitacaoRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ExecutorTransacao executorTransacao;

    @Autowired
    private ValidadorPropostaDuplicada validadorPropostaDuplicada;

    @Autowired
    private OperacaoServicoWebFeign operacaoServicoWebFeign;

    @Autowired
    private PropostaServices propostaServices;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@Valid @RequestBody PropostaRequest propostaRequest,
                                         UriComponentsBuilder builder){
        var digitosFinaisDocumento = propostaRequest.getDocumento().substring(8); //ignora os 8 1° digitos
        logger.info("Iniciando processo da criação da Proposta - ultimos digitos do documento {}", digitosFinaisDocumento);

        if(!validadorPropostaDuplicada.validadoDocumento(propostaRequest)){
            logger.error("ERRO - Criação Proposta - Já existe uma Porposta com esse Documento - ultimos digitos do documento {}", digitosFinaisDocumento);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("ERRO. Já existe uma Porposta com esse Documento."); //retorno 422
        }

        Proposta obj = propostaServices.salvaProposta(propostaRequest); //Chama Service - faz verificação COM/SEM RESTRICAO - salva/altera no BD

        if(obj instanceof Proposta){
            logger.info("Proposta criada com sucesso - ultimos digitos do documento {} - ID Proposta {}", digitosFinaisDocumento, obj.getId());
            return ResponseEntity.created(builder.path("/propostas/{id}").buildAndExpand(obj.getId()).toUri()).build();
        } else {
            //return ResponseEntity.status(400).body(null);
            logger.error("ERRO - Criação Proposta - ultimos digitos do documento {}", digitosFinaisDocumento);
            return ResponseEntity.badRequest().body(null);
        }
    }
}
