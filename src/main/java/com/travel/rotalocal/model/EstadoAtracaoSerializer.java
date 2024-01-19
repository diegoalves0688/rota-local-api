package com.travel.rotalocal.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.travel.rotalocal.model.EstadoAtivo;

import java.io.IOException;

public class EstadoAtracaoSerializer extends StdSerializer<EstadoAtivo> {

    public EstadoAtracaoSerializer() {
        super(EstadoAtivo.class);
    }

    @Override
    public void serialize(EstadoAtivo ativo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString("Ativo"); // Adjust the representation as needed
    }
}
