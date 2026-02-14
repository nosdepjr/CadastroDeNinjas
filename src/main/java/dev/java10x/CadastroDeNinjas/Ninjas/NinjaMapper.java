package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper{

    public NinjaDTO mapToDTO(NinjaModel model) {

        if (model == null) {return null;}

        NinjaDTO dto = new NinjaDTO();
        dto.setId(model.getId());
        dto.setNome(model.getNome());
        dto.setEmail(model.getEmail());
        dto.setIdade(model.getIdade());
        dto.setImgURL(model.getImgURL());
        dto.setRank(model.getRank());
        dto.setMissao(model.getMissao());

        return dto;
    }

    public NinjaModel mapToModel(NinjaDTO dto) {

        if (dto == null) {return null;}

        NinjaModel model = new NinjaModel();
        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEmail(dto.getEmail());
        model.setIdade(dto.getIdade() == null ? 0 : dto.getIdade());
        model.setImgURL(dto.getImgURL());
        model.setRank(dto.getRank());
        model.setMissao(dto.getMissao());

        return model;
    }

    public void updateDTOFromModel(NinjaModel model, NinjaDTO dto) {

        if (model == null || dto == null) {
            return;
        }

        if (dto.getId() == null) {
            dto.setId(model.getId());
        }

        if (dto.getNome() == null) {
            dto.setNome(model.getNome());
        }

        if (dto.getEmail() == null) {
            dto.setEmail(model.getEmail());
        }

        if (dto.getIdade() == null) {
            dto.setIdade(model.getIdade());
        }

        if (dto.getImgURL() == null) {
            dto.setImgURL(model.getImgURL());
        }

        if (dto.getRank() == null) {
            dto.setRank(model.getRank());
        }

        if (dto.getMissao() == null) {
            dto.setMissao(model.getMissao());
        }
    }
}
