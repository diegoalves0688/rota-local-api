package com.travel.rotalocal.exception;

public class AtracaoNotFoundException2 extends RuntimeException {

    public AtracaoNotFoundException2(Long atracaoId) {
        super("esta atracao nao foi localizada - id: " + atracaoId);
    }
}

