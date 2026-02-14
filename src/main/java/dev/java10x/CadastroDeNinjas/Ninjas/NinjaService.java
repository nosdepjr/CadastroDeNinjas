package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService{

    private final NinjaRepository ninjaRepository;
    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Listar todos os meus ninjas
    public List<NinjaDTO> listarNinjas() {
        return ninjaRepository.findAll()
                .stream()
                .map(ninjaMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public NinjaDTO listarNinjaPorId(Long id){
        return ninjaRepository.findById(id)
                .map(ninjaMapper::mapToDTO)
                .orElse(null);
    }

    public NinjaDTO criaNinja(NinjaDTO ninja){
        NinjaModel ninjaModel = ninjaMapper.mapToModel(ninja);
        return ninjaMapper.mapToDTO(ninjaRepository.save(ninjaModel));
    }

    public void deletarNinjaPorId(Long id){
        ninjaRepository.deleteById(id);
    }

    public NinjaDTO alterarNinjaPorId(Long id, NinjaDTO ninjaModificado){
        Optional<NinjaModel> ninjaExistente = ninjaRepository.findById(id);
        if (ninjaExistente.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.mapToModel(ninjaModificado);
            ninjaAtualizado.setId(id);
            return ninjaMapper.mapToDTO(ninjaRepository.save(ninjaAtualizado));
        }

        return null;
    }
}
