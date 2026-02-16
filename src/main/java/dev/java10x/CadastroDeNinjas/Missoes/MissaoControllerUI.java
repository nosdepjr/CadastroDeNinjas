package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/missoes/ui")
public class MissaoControllerUI{

    private final MissaoService missaoService;

    public MissaoControllerUI(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("missoes", missaoService.listarMissoes());
        return "missoes/lista";
    }

    @GetMapping("/listar/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        MissaoDTO missao = missaoService.listarMissaoPorId(id);

        if (missao == null) {
            return "redirect:/missoes/ui/listar";
        }

        model.addAttribute("missao", missao);
        return "missoes/detalhes";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("missao", new MissaoDTO());
        return "missoes/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute MissaoDTO dto, Model model) {
        try {
            missaoService.criarMissao(dto);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return "missoes/form";
        }

        return "redirect:/missoes/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        MissaoDTO missao = missaoService.listarMissaoPorId(id);

        if (missao == null) {
            return "redirect:/missoes/ui/listar";
        }

        model.addAttribute("missao", missao);
        return "missoes/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id,
                            @ModelAttribute MissaoDTO dto) {

        MissaoDTO atualizada = missaoService.alterarMissao(id, dto);

        if (atualizada == null) {
            return "redirect:/missoes/ui/listar";
        }

        return "redirect:/missoes/ui/listar";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, Model model) {
        try {
            missaoService.deletarMissao(id);
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return listar(model);
        }

        return "redirect:/missoes/ui/listar";
    }
}
