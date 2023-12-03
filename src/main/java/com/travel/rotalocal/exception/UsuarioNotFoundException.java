package com.travel.rotalocal.exception;

public class UsuarioNotFoundException extends RuntimeException { 

    public UsuarioNotFoundException(Long id) {
        super("usuario de id '" + id + "' encontrado , triste :( ");
    }
    
}
