package com.monitoramento.service;

import com.monitoramento.model.Leitura;
import com.monitoramento.repository.LeituraRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class LeituraService {
    private final TemperaturaService temperaturaService;
    private final EficienciaService eficienciaService;
    private final LeituraRepository leituraRepository;


    public Leitura registrarLeitura() {
        double temperatura = temperaturaService.geraTemperatura();
        double eficiencia = eficienciaService.calcular(temperatura);
        boolean anormal = eficiencia < 50;
        LocalDateTime dataHora = LocalDateTime.now();
        Leitura leitura = new Leitura(dataHora, temperatura, eficiencia, anormal);
        return leituraRepository.save(leitura);
    }

    public List<Leitura> listarHistorico() {
        return leituraRepository.findAllByOrderByDataHoraDesc();
    }
}