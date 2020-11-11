package com.guiferrini.proposta.propostas;

import com.guiferrini.proposta.servicoWeb.Entity.Cartao;
import com.guiferrini.proposta.servicoWeb.Enums.StatusAvaliacaoProposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, String> {

    List<Proposta> findAllByStatusAndCartaoNull(StatusAvaliacaoProposta status);
    //List<Proposta> findAllByStatus(StatusAvaliacaoProposta status);
}
