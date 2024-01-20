package com.travel.rotalocal.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class EstadoAtracaoSerializer extends StdSerializer<EstadoAtracao> {

    public EstadoAtracaoSerializer() {
        super(EstadoAtracao.class);
    }

    @Override
    public void serialize(EstadoAtracao estadoAtracao, JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString("ativo");
    }
}
