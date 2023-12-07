package com.travel.rotalocal.exception;

public class EmailAlreadyRegisteredException extends RuntimeException {

    public EmailAlreadyRegisteredException(String email) {
        super("o email '" + email + "' ja está registrado - aproveite o rota local.");
    }
}

