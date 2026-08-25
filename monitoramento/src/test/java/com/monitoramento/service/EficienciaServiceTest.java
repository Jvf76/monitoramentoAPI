package com.monitoramento.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EficienciaServiceTest {
   private final EficienciaService service = new EficienciaService();

    @Test
    @DisplayName("Retorna 23 quando a eficiencia esta abaixo do minimo")
    void temperaturaAbaixo() {
        double resultado = service.calcular(20);
        Assertions.assertEquals(23,resultado);
    }

    @Test
    @DisplayName("Retorna 100 quando a eficiencia esta acima no maximo")
    void temperaturaAcima() {
        double resultado = service.calcular(36);
        Assertions.assertEquals(100,resultado);
    }

    @Test
    @DisplayName("Retorna eficiencia proporcional quando a temperatura esta entre os limites")
    void temperaturaEntreOsLimites() {
        double resultado = service.calcular(26.5);
        Assertions.assertEquals(61.5,resultado);
    }

    @Test
    @DisplayName("Retorna 23 quando a tempereatura esta igual a 21")
    void temperaturaExataMinima() {
        double resultado = service.calcular(21);
        Assertions.assertEquals(23,resultado);
    }

    @Test
    @DisplayName("Retorna 23 quando a tempereatura esta igual a 32")
    void temperaturaExataMaximo() {
        double resultado = service.calcular(32);
        Assertions.assertEquals(100,resultado);
    }

}
