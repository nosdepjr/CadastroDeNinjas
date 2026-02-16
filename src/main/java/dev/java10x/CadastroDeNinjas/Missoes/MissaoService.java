package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;
    private final MissaoMapper missaoMapper;
    private final NinjaRepository ninjaRepository;

    public MissaoService(MissaoRepository missaoRepository, MissaoMapper missaoMapper, NinjaRepository ninjaRepository) {
        this.missaoRepository = missaoRepository;
        this.missaoMapper = missaoMapper;
        this.ninjaRepository = ninjaRepository;
    }

    public List<MissaoDTO> listarMissoes() {
        return missaoRepository.findAll()
                .stream()
                .map(missaoMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public MissaoDTO listarMissaoPorId(Long id) {
        return missaoRepository.findById(id)
                .map(missaoMapper::mapToDTO)
                .orElse(null);
    }

    public MissaoDTO criarMissao(MissaoDTO dto) {
        if (missaoRepository.existsByNome(dto.getNome())) {
            throw new RuntimeException("Já existe uma missão com esse nome.");
        }

        MissaoModel model = missaoMapper.mapToModel(dto);
        return missaoMapper.mapToDTO(missaoRepository.save(model));
    }

    public MissaoDTO alterarMissao(Long id, MissaoDTO dto) {
        Optional<MissaoModel> missaoOptional = missaoRepository.findById(id);

        if (missaoOptional.isEmpty()) {
            return null;
        }

        MissaoModel missaoExistente = missaoOptional.get();

        missaoMapper.updateModelFromDTO(missaoExistente, dto);

        return missaoMapper.mapToDTO(missaoRepository.save(missaoExistente));
    }

    public boolean deletarMissao(Long id) {
        Optional<MissaoModel> missaoOptional = missaoRepository.findById(id);

        if (missaoOptional.isEmpty()) {
            return false;
        }

        MissaoModel missao = missaoOptional.get();

        if (missao.getNinjas() != null && !missao.getNinjas().isEmpty()) {
            throw new RuntimeException(
                    "Não é possível excluir a missão. Existem ninjas vinculados a ela."
            );
        }

        missaoRepository.deleteById(id);
        return true;
    }
}