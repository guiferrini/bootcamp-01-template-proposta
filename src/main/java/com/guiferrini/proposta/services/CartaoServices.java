package com.guiferrini.proposta.services;

import com.guiferrini.proposta.propostas.Proposta;
import com.guiferrini.proposta.propostas.PropostaRepository;
import com.guiferrini.proposta.servicoWeb.CriacaoCartaoWebFeign;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartaoServices {

    private final Logger logger = LoggerFactory.getLogger(CartaoServices.class);

    @Autowired
    private CriacaoCartaoWebFeign criacaoCartaoWebFeign;

    @Autowired
    private PropostaServices propostaServices;

    //@Autowired
    private Proposta proposta;

    @Autowired
    private PropostaRepository propostaRepository;

    @Scheduled(fixedDelay = 20000) //1min 20000 - 10min 600000milli
    @Transactional
    public void propostaAssociaComCartao(){

        logger.info("Verificando se existe Proposta ELEGIVEL para criar CARTÃO - classe CartaoServices - propostaAssociaComCartao");

        var elegiveisESemCartao = propostaRepository.findAllByStatusAndCartaoNull(StatusAvaliacaoProposta.ELEGIVEL);

        //Try/catch p tratar exceção
        elegiveisESemCartao.forEach(proposta -> {
            try {
                logger.info("Iniciado processo de criação do Cartão");
                var criacaoCartao = criacaoCartaoWebFeign.solicitaCartaoPorId(proposta.getId());
                var cartao = criacaoCartao.toModel();

                proposta.setCartao(cartao); //proposta.adicionaCartao(cartao); //setCartao - alternativa
                proposta.setStatus(StatusAvaliacaoProposta.CONCLUIDA); //garante que proposta já tem catrao e portanto esta concluida.

                propostaRepository.save(proposta);
                logger.info("Criação cartao ID {} - Proposta ID {}", proposta.getCartao().getId(), proposta.getId());

            } catch (FeignException exception){
                logger.error("ERRO ao associar cartao a proposta. ID da Proposta: {}, ERRO no Sistema de cartões: {} ", proposta.getId(),
                        exception.getMessage());
            }
        });
    }
}
