package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUI {

    private final NinjaService ninjaService;

    public NinjaControllerUI(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("ninjas", ninjaService.listarNinjas());
        return "ninjas/lista";
    }

    @GetMapping("/listar/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);

        if (ninja == null) {
            return "redirect:/ninjas/ui/listar";
        }

        model.addAttribute("ninja", ninja);
        return "ninjas/detalhes";
    }

    @GetMapping("/adicionar")
    public String adicionar(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        return "ninjas/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute NinjaDTO ninja) {
        ninjaService.criaNinja(ninja);
        return "redirect:/ninjas/ui/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);

        if (ninja == null) {
            return "redirect:/ninjas/ui/listar";
        }

        model.addAttribute("ninja", ninja);
        return "ninjas/form";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute NinjaDTO ninja) {
        NinjaDTO ninjaAtualizado = ninjaService.alterarNinjaPorId(id, ninja);

        if (ninjaAtualizado == null) {
            return "redirect:/ninjas/ui/listar";
        }

        return "redirect:/ninjas/ui/listar";
    }

    @PostMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        ninjaService.deletarNinjaPorId(id);
        return "redirect:/ninjas/ui/listar";
    }
}
