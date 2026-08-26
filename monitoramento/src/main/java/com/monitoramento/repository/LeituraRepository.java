package com.monitoramento.repository;

import com.monitoramento.model.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeituraRepository extends JpaRepository <Leitura,Long>{
    List<Leitura> findAllByOrderByDataHoraDesc();


}
