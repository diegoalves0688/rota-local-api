package com.travel.rotalocal.model;

import com.travel.rotalocal.model.entity.Atracao;

public interface EstadoAtracao {

    void ativar(Atracao atracao);

    void inativar(Atracao atracao);
}
