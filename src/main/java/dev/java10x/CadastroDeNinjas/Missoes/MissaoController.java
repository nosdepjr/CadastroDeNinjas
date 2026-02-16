package dev.java10x.CadastroDeNinjas.Missoes;

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
@RequestMapping("/missoes")
public class MissaoController {
    // CRUD (Create, Read, Update, Delete)

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @Operation(summary = "Listar todas as missões",
            description = "Retorna uma lista com todas as missões cadastradas.")
    @ApiResponse(responseCode = "200",
            description = "Lista de missões retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MissaoDTO.class)))
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes() {
        return ResponseEntity.ok(missaoService.listarMissoes());
    }

    @Operation(summary = "Buscar missão por ID",
            description = "Retorna uma missão específica com base no ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Missão encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MissaoDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Missão não encontrada",
                    content = @Content)
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        MissaoDTO missao = missaoService.listarMissaoPorId(id);

        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não encontrada.");
        }

        return ResponseEntity.ok(missao);
    }

    @Operation(summary = "Criar nova missão",
            description = "Cria uma nova missão no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Missão criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MissaoDTO.class))),
            @ApiResponse(responseCode = "400",
                    description = "Erro na criação da missão",
                    content = @Content)
    })
    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody MissaoDTO dto) {
        try {
            MissaoDTO criada = missaoService.criarMissao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(criada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @Operation(summary = "Alterar missão existente",
            description = "Atualiza os dados de uma missão existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Missão atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MissaoDTO.class))),
            @ApiResponse(responseCode = "404",
                    description = "Missão não encontrada",
                    content = @Content)
    })
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody MissaoDTO dto) {
        MissaoDTO atualizada = missaoService.alterarMissao(id, dto);

        if (atualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não encontrada.");
        }

        return ResponseEntity.ok(atualizada);
    }

    @Operation(summary = "Deletar missão",
            description = "Remove uma missão do sistema. Não permite exclusão se houver ninjas vinculados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Missão deletada com sucesso",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "Missão não encontrada",
                    content = @Content),
            @ApiResponse(responseCode = "409",
                    description = "Conflito: missão possui ninjas vinculados",
                    content = @Content)
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            boolean deletada = missaoService.deletarMissao(id);

            if (!deletada) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Missão com ID (" + id + ") não encontrada.");
            }

            return ResponseEntity.ok("Missão deletada com sucesso.");

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
