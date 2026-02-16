package dev.java10x.CadastroDeNinjas.Missoes;

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

    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissoes() {
        return ResponseEntity.ok(missaoService.listarMissoes());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id) {
        MissaoDTO missao = missaoService.listarMissaoPorId(id);

        if (missao == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não encontrada.");
        }

        return ResponseEntity.ok(missao);
    }

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

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody MissaoDTO dto) {
        MissaoDTO atualizada = missaoService.alterarMissao(id, dto);

        if (atualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID (" + id + ") não encontrada.");
        }

        return ResponseEntity.ok(atualizada);
    }

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
