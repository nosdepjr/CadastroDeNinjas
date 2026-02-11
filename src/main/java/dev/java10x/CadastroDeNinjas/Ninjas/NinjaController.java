package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ninjas")
public class NinjaController{
    // CRUD (Create, Read, Update, Delete)
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Essa é a minha primeira rota. Hello World!";
    }

    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    @GetMapping("/listar")
    public String listarNinjas() {
        return "Mostrar Ninja";
    }

    @GetMapping("/listarID")
    public String listarNinjaPorId() {
        return "Mostrar Ninja por id";
    }

    @PutMapping("/alterarID")
    public String alterarNinjaPorId() {
        return "Alterar Ninja por id";
    }

    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId() {
        return "Ninja deletado por id";
    }
}
