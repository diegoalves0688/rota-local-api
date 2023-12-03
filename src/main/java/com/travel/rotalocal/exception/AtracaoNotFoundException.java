package com.travel.rotalocal.exception;


public class AtracaoNotFoundException extends RuntimeException { 

    public AtracaoNotFoundException(Long usuarioId, Long localizacaoId) {
        
        super("a atracao criada pelo usuario com id: '" + usuarioId + 
        "' e com localizacao de id: '" + localizacaoId + "' nao existe, triste");
    }
}
