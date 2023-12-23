package com.travel.rotalocal.exception;

public class RecomendacaoAtracaoNotFoundException2 extends RuntimeException {

    public RecomendacaoAtracaoNotFoundException2(Long id) {
        super("a recomendacao da atracao selecionada nao foi encontrada - id: " + id);
    }
}