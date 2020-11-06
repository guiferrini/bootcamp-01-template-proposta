//package com.guiferrini.proposta.servicoWeb;
//
//import com.guiferrini.proposta.propostas.Proposta;
//import com.guiferrini.proposta.servicoWeb.Enums.ResultadoComOuSem;
//import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AvaliaProposta {
//
//    @Autowired
//    private OperacaoServicoWebFeign operacaoServicoWebFeign;
//
//    public StatusAvaliacaoProposta executa(Proposta proposta){
//
//        String resultadoAvaliacao =  operacaoServicoWebFeign.avalia(new SolicitacaoRequest(proposta));
//
//        return ResultadoComOuSem.valueOf(resultadoAvaliacao).getStatusAvaliacaoProposta();
//    }
//
//    public OperacaoServicoWebFeign getOperacaoServicoWebFeign() {
//        return operacaoServicoWebFeign;
//    }
//}
