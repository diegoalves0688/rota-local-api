package com.travel.rotalocal.exception;

public class AvaliacaoRecomendacaoDuplicatedException extends RuntimeException { 
    
    public AvaliacaoRecomendacaoDuplicatedException(Long usuarioId, Long recomendacaoId) {
        
        super("o usuario de id '" + usuarioId + "' ja avaliou a recomendacao de id'" + recomendacaoId + "' .");
    }
}
