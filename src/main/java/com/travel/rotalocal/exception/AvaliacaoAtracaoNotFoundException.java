package com.travel.rotalocal.exception;

public class AvaliacaoAtracaoNotFoundException extends RuntimeException { 
    
    public AvaliacaoAtracaoNotFoundException(Long usuarioId, Long atracaoId) {
        
        super("a avaliacao da atracao criada pelo usuario com id: '" + usuarioId + 
        "' e com atracao de id: '" + atracaoId + "' nao existe, triste");
    }
    
}

