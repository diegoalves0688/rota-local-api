package com.travel.rotalocal.model;

import com.travel.rotalocal.model.entity.Atracao;

public class EstadoInativo implements EstadoAtracao {

    @Override
    public void inativar(Atracao atracao) {
        System.out.println("opa! atracao já inativa...");
    }

    @Override
    public void ativar(Atracao atracao) {
        atracao.setAtivo(true);
        System.out.println("atracao ativada com sucesso...");
    }
}