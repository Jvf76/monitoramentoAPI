package com.monitoramento.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class TemperaturaService {
    public double geraTemperatura() {
        return ThreadLocalRandom.current().nextDouble(15, 40);
       // throw new RuntimeException("Falha simulada");
    }
}
