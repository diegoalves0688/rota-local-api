package com.travel.rotalocal.exception;

public class RecomendacaoAtracaoDuplicatedException extends RuntimeException { 

    public RecomendacaoAtracaoDuplicatedException(Long usuarioId, Long atracaoId) {
        
        super("o usuario de id '" + usuarioId + "' ja escreveu recomendacao para a atracao de id'" + atracaoId + "'.");
    }
    
}
