package com.guiferrini.proposta.servicoWeb;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {
}
