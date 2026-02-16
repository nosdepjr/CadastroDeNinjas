package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissaoMapper {

    public MissaoDTO mapToDTO(MissaoModel model) {
        Integer totalNinjas = 0;

        if (model.getNinjas() != null) {
            totalNinjas = model.getNinjas().size();
        }

        return new MissaoDTO(
                model.getId(),
                model.getNome(),
                model.getDificuldade(),
                totalNinjas
        );
    }

    public MissaoModel mapToModel(MissaoDTO dto) {
        MissaoModel model = new MissaoModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setDificuldade(dto.getDificuldade());

        return model;
    }

    public void updateModelFromDTO(MissaoModel model, MissaoDTO dto) {
        model.setNome(dto.getNome());
        model.setDificuldade(dto.getDificuldade());
    }
}