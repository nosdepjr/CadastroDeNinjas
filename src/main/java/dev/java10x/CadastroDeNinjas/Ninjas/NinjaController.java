package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController{
    // CRUD (Create, Read, Update, Delete)

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Essa é a minha primeira rota. Hello World!";
    }

    @PostMapping("/criar")
    public ResponseEntity<NinjaDTO> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaDTO = ninjaService.criaNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarNinjaPorId(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listarNinjaPorId(id);
        if(ninja != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(ninja);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com ID (" + id + ") inexistente");
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarNinjaPorId(@PathVariable Long id, @RequestBody NinjaDTO ninjaModificado) {
        NinjaDTO ninjaBody = ninjaService.alterarNinjaPorId(id, ninjaModificado);
        if(ninjaBody != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(ninjaBody);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com ID (" + id + ") inexistente");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorId(@PathVariable Long id) {
        if(ninjaService.listarNinjaPorId(id) != null){
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com ID (" + id + ") deletado com sucesso");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Ninja com ID (" + id + ") inexistente");
    }
}
