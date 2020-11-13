package com.guiferrini.proposta.servicoWeb.Entity;

import com.guiferrini.proposta.biometria.Biometria;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name="cartao")
@EnableScheduling
public class Cartao {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private UUID idCartaoEmitido;

    @CreationTimestamp
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Biometria> biometria = new HashSet<>();

    @Deprecated
    public Cartao(){
    }

    public Cartao(@NotNull UUID idCartaoEmitido,
                  LocalDateTime emitidoEm,
                  @NotBlank String titular) {
        this.idCartaoEmitido = idCartaoEmitido;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
    }

    public String getId() {
        return id;
    }

    public void adicionaBiometria(Biometria novaBiometria){
        biometria.add(novaBiometria);
    }
}
