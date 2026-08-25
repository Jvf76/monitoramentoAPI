package com.monitoramento.service;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public class TemperaturaService {
    public double temperatura(double temperatura) {
        return ThreadLocalRandom.current().nextFloat(15, 50);
    }
}
