package com.guiferrini.proposta.biometria;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "biometria")
public class Biometria {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    private byte[] digital;

    //@CreationTimestamp - alternativa
    @NotNull
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @Deprecated
    public Biometria(){
    }

    public Biometria(@NotNull String digital) {
        this.digital = digital.getBytes();
    }

    public String getId() {
        return id;
    }
}
