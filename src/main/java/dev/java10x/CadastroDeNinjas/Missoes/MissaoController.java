package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissaoController{
    // CRUD (Create, Read, Update, Delete)
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Essa é a primeira rota das missoes.Hello World!";
    }

    @GetMapping("/listar")
    public String listarMissoes() {
        return "Missoes listadas com sucesso";
    }

    @PostMapping("/criar")
    public String criarMissao() {
        return "Missao criada com sucesso";
    }

    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso";
    }

    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "Missao deletada com sucesso";
    }
}
