package com.monitoramento.controller;

import com.monitoramento.service.LeituraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LeituraController {
    private final LeituraService service;

    @GetMapping("/")
    public String listHistoric(Model model) {
        var historico = service.listarHistorico();
        model.addAttribute("historico", historico);

        if (!historico.isEmpty()){
            model.addAttribute("leituraAtual",historico.get(0));
        }

        return "index";
    }

    @PostMapping("/atualizar")
    public String updateHistoric(RedirectAttributes redirectAttributes) {
        try {
            service.registrarLeitura();
        }catch (RuntimeException exception){
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Não foi possível obter uma nova leitura. Tente novamente"
            );


        }
        return "redirect:/";
    }
}
