package com.monitoramento.service;

import org.springframework.stereotype.Service;

@Service
public class EficienciaService {

    public double calcular(double temperatura) {
        if (temperatura < 21) {
            return 23;
        } else if (temperatura >= 32) {
            return 100;
        } else {
            double diferenca = temperatura - 21;

            double eficiencia = diferenca * 7;

            return eficiencia + 23;
        }


    }
}
