package com.travel.rotalocal.exception;

public class ImagemNotFoundException extends RuntimeException { 
    public ImagemNotFoundException(Long usuarioId, Long atracaoId) {
        
        super("a atracao criada pelo usuario com id: '" + usuarioId + 
        "' e com atracao de id: '" + atracaoId + "' nao existe, triste");
    }
    
}

