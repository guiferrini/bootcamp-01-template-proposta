package com.guiferrini.proposta.services;

import com.guiferrini.proposta.propostas.Proposta;
import com.guiferrini.proposta.propostas.PropostaRepository;
import com.guiferrini.proposta.servicoWeb.CartaoRepository;
import com.guiferrini.proposta.servicoWeb.CriacaoCartaoWebFeign;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
//@EnableScheduling
public class CartaoServices {

    private final Logger logger = LoggerFactory.getLogger(CartaoServices.class);

    @Autowired
    private CriacaoCartaoWebFeign criacaoCartaoWebFeign;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PropostaServices propostaServices;

    //@Autowired
    private Proposta proposta;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Scheduled(fixedDelay = 2000)
    @Transactional
    public void propostaAssociaComCartao(){
        logger.info("Inicio do processo para verificar se é possível criar Cartão - classe CartaoServices - propostaAssociaComCartao");

        //chamar as propostas Elegíveis e sem cartao - metodo listaElegiveisESemCartao (dentro da classe PropostaService)
        //List<Proposta> elegiveisESemCartao = propostaServices.listaElegiveisESemCartao();
        //List<Proposta> elegiveisESemCartao = propostaRepository.findAllByStatusAndCartaoNull(StatusAvaliacaoProposta.ELEGIVEL);

        var elegiveisESemCartao = propostaRepository.findAllByStatusAndCartaoNull(StatusAvaliacaoProposta.ELEGIVEL);


        //System.out.println(elegiveisESemCartao.stream().findFirst().map(vai -> vai.getId())); //lista com as propostas

        //chama o Feign p fazer verificação com legado(externo)
        //Try/catch p tratar exceção
        elegiveisESemCartao.forEach(proposta -> {
            try {
                var criacaoCartao = criacaoCartaoWebFeign.solicitaCartaoPorId(proposta.getId());

                var cartao = criacaoCartao.toModel();

                proposta.setCartao(cartao); //proposta.adicionaCartao(cartao); //setCartao - alternativa
                proposta.setStatus(StatusAvaliacaoProposta.CONCLUIDA); //garante que proposta já tem catrao e portanto esta concluida.

                propostaRepository.save(proposta);

                logger.info("Criação cartao ID {}", proposta.getCartao().getId());
            } catch (FeignException exception){
                logger.error("ERRO ao associar cartao a proposta. ID da Proposta: {}, ERRO no Sistema de cartões: {} ", proposta.getId(),
                        exception.getMessage());
            }
        });


        //n tem requisição minha, tem q ir no BD e buscar o ID (elegivel e sem cartao) e ai sim inicia o processo de criação do cartao!
        //criar repository?!?!

        //- Onde fazer - o q fazer
        //ok 0 Proposta - Criar em Proposta coluna 'cartao': null ou com String(id do cartão)
        //ok 1 PropostasService - buscar propostas elegiveis e sem cartao -> PropostasService (criar metodo p listar propostas elegiveis e sem cartao)
        //2 Scheduled chama?! - associar a proposta ao cartao (chama médodo do passo 1 e chama Feign ('solicitaCartaoPorId') p efetuar a associação)
        //Proximo Exercicio 030 - salva na Proposta o n° do cartao
    }

}
