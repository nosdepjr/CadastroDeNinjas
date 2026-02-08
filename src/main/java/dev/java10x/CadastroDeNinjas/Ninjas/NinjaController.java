package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ninjas")
public class NinjaController{

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Essa é a minha primeira rota. Hello World!";
    }
}
