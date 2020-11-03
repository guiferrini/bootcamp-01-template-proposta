//package com.guiferrini.proposta;
//
//import com.guiferrini.proposta.propostas.Proposta;
//import com.guiferrini.proposta.propostas.PropostaController;
//import com.guiferrini.proposta.propostas.PropostaRequest;
//import com.guiferrini.proposta.propostas.ValidadorPropostaDuplicada;
//import org.junit.jupiter.api.Assertions;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.persistence.EntityManager;
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//public class PropostaControllerTest {
//
//    @Test
//    @DisplayName("Não aceitar Documento igual/Duplicado")
//    void test(){
////        EntityManager entityManager = Mockito.mock(EntityManager.class);
////        ValidadorPropostaDuplicada validadorPropostaDuplicada = Mockito.mock(ValidadorPropostaDuplicada.class);
////
////        Proposta proposta = new Proposta("691.001.540-16", "gui@zup.combr", "gui", "rua um", new BigDecimal("100.0"));
////        PropostaRequest propostaRequest = new PropostaRequest("691.001.540-16", "gui@zup.combr", "gui", "rua um", new BigDecimal("100.0"));
////        PropostaController propostaController = new PropostaController();
////
//////        when(entityManager.find(propostaRequest.getClass(), propostaRequest.getDocumento())).thenReturn(Optional.empty());
////
////        ResponseEntity<?> responseEntity = propostaController.cria(propostaRequest, UriComponentsBuilder.newInstance());
////        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//
//
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        ValidadorPropostaDuplicada validadorPropostaDuplicada = Mockito.mock(ValidadorPropostaDuplicada.class);
//        PropostaController propostaController = new PropostaController();
//        PropostaRequest propostaRequest = new PropostaRequest(
//                "813.301.730-07", "gui@zup.combr", "gui", "rua um", new BigDecimal("100.0")
//        );
//        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
//
////        Assertions.assertThrows(() -> {propostaController.cria(propostaRequest, builder);
////        });
//
//        Mockito.when(validadorPropostaDuplicada.validadoDocumento(propostaRequest)).thenReturn(true);
//        ResponseEntity<?> response = propostaController.cria(propostaRequest, builder);
//
//        Proposta propostaQueDeviaSerGerada = propostaRequest.toModel();
//        Mockito.verify(entityManager).persist(propostaQueDeviaSerGerada);
//        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
//
//    }
//// A referencia n eh a msm - retorna nulo..
////        @Test
////        @DisplayName("deve salvar se o documento está válido")
////        void teste2(){
////            EntityManager manager = Mockito.mock(EntityManager.class);
////
////            ValidadorPropostaDuplicada validadorDocumento = Mockito.mock(ValidadorPropostaDuplicada.class);
////            PropostaController controller = new PropostaController();
////            PropostaRequest request = new PropostaRequest("111111111", "email@eamil.com",
////                    "Alberto", "endereco", new BigDecimal("1000"));
////
////            UriComponentsBuilder builder2 =  UriComponentsBuilder.newInstance();
////
////            /*
////             * O Mockito verifica se o objeto que representa a request de nova proposta
////             * que vai ser passado como argumento lá dentro do método cria é de  fato o
////             * mesmo objeto que está sendo esperando na definicao da expectativa
////             */
////            Mockito.when(validadorDocumento.validadoDocumento(request)).thenReturn(true);
////            ResponseEntity<?> response = controller.cria(request, builder2);
////
////            Proposta propostaQueDeviaSerGerada = request.toModel();
////            Mockito.verify(manager).persist(propostaQueDeviaSerGerada);
////            Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
////        }
//
//}
