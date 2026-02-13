-- V3: Migration para adicionar a coluna de IMG_URL na tabela de cadastro de ninjas

ALTER TABLE tb_ninjas
ADD COLUMN img_url VARCHAR(255);