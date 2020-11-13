package com.guiferrini.proposta.biometria;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BiometriaRequest {

    @NotNull
    @Base64
    private String digital;

    @Deprecated
    public BiometriaRequest(){
    }

//    public BiometriaRequest(@NotBlank String digital) {
//        this.digital = digital;
//    }

    public String getDigital() {
        return digital;
    }

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public Biometria toModel(){
        return new Biometria(digital);
    }
}
