package com.guiferrini.proposta.servicoWeb;

import com.guiferrini.proposta.servicoWeb.Request.ResultadoAnaliseRequest;
import com.guiferrini.proposta.servicoWeb.Request.SolicitacaoRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(url = "${feign.analise-url}", name = "operacaoServicoWebFeign")
//@FeignClient(url = "http://localhost:9999", name = "operacaoServicoWebFeign")
public interface OperacaoServicoWebFeign {

    @PostMapping("/api/solicitacao")
    //public String avalia(Solicitacao solicitacao);
    ResultadoAnaliseRequest analiseDadosFinanceiros(@RequestBody SolicitacaoRequest solicitacaoRequest);

}