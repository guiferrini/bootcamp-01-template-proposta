package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
import com.guiferrini.proposta.servicoWeb.OperacaoServicoWebFeign;
import com.guiferrini.proposta.servicoWeb.SolicitacaoRequest;
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

import static com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta.NAO_ELEGIVEL;


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

    @PostMapping
    @Transactional
    public ResponseEntity<?> cria(@Valid @RequestBody PropostaRequest propostaRequest,
                                         UriComponentsBuilder builder){
        if(!validadorPropostaDuplicada.validadoDocumento(propostaRequest)){
            logger.error("ERRO - Criação Proposta - Já existe uma Porposta com esse Documento n° {}", propostaRequest.getDocumento());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("ERRO. Já existe uma Porposta com esse Documento."); //retorno 422
        }

        Proposta obj = propostaRequest.toModel();
        //executorTransacao.salvaEcomita(obj);
        entityManager.persist(obj);

        var solicitacaoRequest = new SolicitacaoRequest(obj);
        System.out.println("solicitacaoRequest: " + solicitacaoRequest);

        //Tradando o Erro, COM_RESTRICAO... Criar service ou em outro lugar...
        try{
            var resultadoAnalise = operacaoServicoWebFeign.analiseDadosFinanceiros(solicitacaoRequest);
            obj.aplicaResultadoAnalise(resultadoAnalise.getResultadoComOuSem());
        } catch (FeignException.UnprocessableEntity exception){
            obj.aplicaResultadoAnalise(ResultadoComOuSem.COM_RESTRICAO);
        }

        executorTransacao.salvaEcomita(obj);
        //entityManager.merge(obj);

        if(obj instanceof Proposta){
            return ResponseEntity.created(builder.path("/propostas/{id}").buildAndExpand(obj.getId()).toUri()).build();
        } else {
            //return ResponseEntity.status(400).body(null);
            logger.error("ERRO - Criação Proposta");
            return ResponseEntity.badRequest().body(null);
        }
    }
}
