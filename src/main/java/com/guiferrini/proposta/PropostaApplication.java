package com.guiferrini.proposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableFeignClients // anotação indica para o Spring Framework que nosso projeto vai utilizar a configuração de clientes web services dinâmicas.
@EnableScheduling
@SpringBootApplication
public class PropostaApplication {

	public static void main(String[] args) {

		SpringApplication.run(PropostaApplication.class, args);

	}

}
