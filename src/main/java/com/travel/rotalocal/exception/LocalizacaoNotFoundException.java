package com.travel.rotalocal.exception;

public class LocalizacaoNotFoundException extends RuntimeException { 

        public LocalizacaoNotFoundException(Long id) {
            super("localizacao de id '" + id + "' nao esta registrada , triste :( ");
        }
}
