package com.travel.rotalocal.exception;

public class AvaliacaoRecomendacaoNotFoundException extends RuntimeException { 

    public AvaliacaoRecomendacaoNotFoundException(Long usuarioId, Long recomendacaoId) {
        
        super("a recomendacao criada pelo usuario com id: '" + usuarioId + 
        "' e de id: '" + recomendacaoId + "' nao existe, triste");
    }
    
}
