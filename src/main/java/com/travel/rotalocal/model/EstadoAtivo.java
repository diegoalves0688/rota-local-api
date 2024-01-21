package com.travel.rotalocal.model;

import com.travel.rotalocal.model.entity.Atracao;

public class EstadoAtivo implements EstadoAtracao {

    @Override
    public void ativarAtracao(Atracao atracao) {
        System.out.println("opa! atracao já ativa...");
    }

    @Override
    public void inativarAtracao(Atracao atracao) {
        atracao.setAtivo(false);
        System.out.println("atracao inativada com sucesso...");

    }
}
