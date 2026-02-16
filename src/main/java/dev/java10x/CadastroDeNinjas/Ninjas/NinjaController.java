package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Mensagem de teste",
            description = "Retorna uma mensagem simples para verificar se a API está funcionando.")
    @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso")
    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "Essa é a minha primeira rota. Hello World!";
    }

    @Operation(
            summary = "Criar um novo Ninja",
            description = "Cria um novo ninja no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ninja criado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = NinjaDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro na requisição"
            )
    })
    @PostMapping("/criar")
    public ResponseEntity<?> criarNinja(@RequestBody NinjaDTO ninja) {
        try {
            NinjaDTO ninjaDTO = ninjaService.criaNinja(ninja);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ninjaDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @Operation(
            summary = "Listar todos os Ninjas",
            description = "Retorna uma lista com todos os ninjas cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista retornada com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = NinjaDTO.class)
                    )
            )
    })
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @Operation(
            summary = "Buscar Ninja por ID",
            description = "Retorna um ninja específico com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ninja encontrado",
                    content = @Content(
                            schema = @Schema(implementation = NinjaDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ninja não encontrado"
            )
    })
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

    @Operation(
            summary = "Atualizar Ninja",
            description = "Atualiza os dados de um ninja existente pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ninja atualizado com sucesso",
                    content = @Content(
                            schema = @Schema(implementation = NinjaDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ninja não encontrado"
            )
    })
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

    @Operation(
            summary = "Deletar Ninja",
            description = "Remove um ninja do sistema com base no ID informado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ninja deletado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ninja não encontrado"
            )
    })
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
