package com.travel.rotalocal.exception;

public class AvaliacaoAtracaoDuplicatedException extends RuntimeException { 

    public AvaliacaoAtracaoDuplicatedException(Long usuarioId, Long atracaoId) {
        
        super("o usuario de id '" + usuarioId + "' ja avaliou a atracao de id'" + atracaoId + "'.");
    }
}
