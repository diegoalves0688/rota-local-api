package com.travel.rotalocal.model;

import com.travel.rotalocal.model.entity.Atracao;

public interface EstadoAtracao {

    void ativarAtracao(Atracao atracao);

    void inativarAtracao(Atracao atracao);
}
