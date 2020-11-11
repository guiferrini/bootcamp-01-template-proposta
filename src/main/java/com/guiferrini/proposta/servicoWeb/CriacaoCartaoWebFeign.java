package com.guiferrini.proposta.servicoWeb;

import com.guiferrini.proposta.servicoWeb.Request.CartaoRequest;
import com.guiferrini.proposta.servicoWeb.Response.CartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(url = "${feign.cartao-url}", name = "CriacaoCartaoWebFeign")
@FeignClient(url = "http://localhost:8888", name = "CriacaoCartaoWebFeign")
public interface CriacaoCartaoWebFeign {

    @GetMapping("/api/cartoes")
    CartaoResponse solicitaCartaoPorId(@RequestParam String idProposta);
}
