package com.monitoramento.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "leituras")
@Getter
public class Leitura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    private double temperatura;
    private double eficiencia;
    private boolean anormal;

    public Leitura() {

    }

    public Leitura(LocalDateTime dataHora, double temperatura, double eficiencia, boolean anormal) {
        this.dataHora = dataHora;
        this.temperatura = temperatura;
        this.eficiencia = eficiencia;
        this.anormal = anormal;
    }


}
