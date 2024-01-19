package com.travel.rotalocal.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.travel.rotalocal.model.EstadoAtivo;

import java.io.IOException;

public class EstadoAtracaoDeserializer extends StdDeserializer<EstadoAtivo> {

    public EstadoAtracaoDeserializer() {
        super(EstadoAtivo.class);
    }

    @Override
    public EstadoAtivo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        // Parse the JSON representation and return the appropriate EstadoAtracao
        // implementation
        return new EstadoAtivo(); // Adjust the instantiation as needed
    }
}
