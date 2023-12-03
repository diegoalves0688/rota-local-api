package com.travel.rotalocal.exception;

public class RecomendacaoAtracaoNotFoundException extends RuntimeException { 
    
    public RecomendacaoAtracaoNotFoundException(Long usuarioId, Long atracaoId) {
        
        super("a recomendacao da atracao criada pelo usuario com id: '" + usuarioId + 
        "' e com atracao de id: '" + atracaoId + "' nao existe, triste");
    }
    
}
