package com.guiferrini.proposta.services;

import com.guiferrini.proposta.propostas.*;
import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
import com.guiferrini.proposta.servicoWeb.OperacaoServicoWebFeign;
import com.guiferrini.proposta.servicoWeb.Request.SolicitacaoRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggerGroup;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem.COM_RESTRICAO;

@Service
public class PropostaServices {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @PersistenceContext
    EntityManager entityManager;

    private Proposta proposta;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ExecutorTransacao executorTransacao;

    @Autowired
    private ValidadorPropostaDuplicada validadorPropostaDuplicada;

    @Autowired
    private OperacaoServicoWebFeign operacaoServicoWebFeign;

    @Transactional
    public Proposta salvaProposta(PropostaRequest propostaRequest){

        var digitosFinaisDocumento = propostaRequest.getDocumento().substring(8); //ignora os 8 1° digitos

        Proposta obj = propostaRequest.toModel();
        propostaRepository.save(obj);

        logger.info("Proposta salva no BD - ultimos digitos do documento {}", digitosFinaisDocumento);
        //executorTransacao.salvaEcomita(obj);
        //entityManager.persist(obj);

        var solicitacaoRequest = new SolicitacaoRequest(obj);

        //Tradando o Erro, COM_RESTRICAO...
        try{
            var resultadoAnalise = operacaoServicoWebFeign.analiseDadosFinanceiros(solicitacaoRequest);
            //System.out.println(resultadoAnalise.getResultadoSolicitacao());

            var propostaStatus = resultadoAnalise.getResultadoSolicitacao().toPropostaStatus();
            //System.out.println(propostaStatus);

            obj.setStatus(propostaStatus);

            //obj.aplicaResultadoAnalise(resultadoAnalise.getStatus());
        } catch (FeignException exception){
            //obj.aplicaResultadoAnalise(COM_RESTRICAO);
            logger.warn("ERRO. FeignException - Consulta a Analise com ERRO {}", exception.contentUTF8());
        }

        propostaRepository.save(obj);
        logger.info("Proposta alterada: COM ou SEM restrição no BD - ultimos digitos do documento {}", digitosFinaisDocumento);
        //executorTransacao.salvaEcomita(obj);
        //entityManager.merge(obj);

        return obj;
    }

    //Busca Propostas com Status:Elegível e Sem Cartão associado
//    public List<Proposta> listaElegiveisESemCartao(){
//
////        return propostaRepository.findAllByStatusAndCartao(StatusAvaliacaoProposta.ELEGIVEL, null);
//
////        var idProposta = entityManager.find(Proposta.class, proposta.getId());
////        List<Proposta> elegiveisESemCartao = new ArrayList<>();
////        //busca cartao
////        var id = entityManager.find(Proposta.class, idProposta); //se n passa nada, como buscar o id da proposta e fazer validações
////
////        //verifica se é ELEGIVEL
////        if(id.getStatus().equals(1)) {
////            //verifica se cartao = null -> n tem cartão associado
////            if(id.getCartao().equals(null)){
////                elegiveisESemCartao.add(id);
////                return elegiveisESemCartao;
////            }
////
////            //Criar na Proposta Cartao, check se tem q criar tb no PropostaRequest - ?!
////        }
////
////        return null;
////        //verificar se proposta esta sem cartao - elegivel = 1  -e salva em uma lista, q será consultado no CartaoService
//
//    }
}
