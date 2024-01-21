package com.travel.rotalocal.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.travel.rotalocal.model.entity.Atracao;
import com.travel.rotalocal.service.AtracaoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

public class EstadoAtracaoDeserializer extends StdDeserializer<EstadoAtracao> {

    @Autowired
    private AtracaoService atracaoService;

    public EstadoAtracaoDeserializer() {
        super(EstadoAtracao.class);
    }

    @Override
    public EstadoAtracao deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        Long atracaoId = extractAtracaoIdFromRequestURI(jsonParser.getCurrentLocation().getSourceRef().toString());
        Atracao atracao = atracaoService.getAtracaoById(atracaoId);

        String action = extractActionFromRequestURI(jsonParser.getCurrentLocation().getSourceRef().toString());
        if ("ativar".equals(action)) {
            atracao.ativarAtracao();
        } else if ("inativar".equals(action)) {
            atracao.inativarAtracao();
        }

        return atracao.getEstadoAtracao();
    }

    private Long extractAtracaoIdFromRequestURI(String uri) {
        String[] parts = uri.split("/");
        return Long.parseLong(parts[parts.length - 2]);
    }

    private String extractActionFromRequestURI(String uri) {
        String[] parts = uri.split("/");
        return parts[parts.length - 1];
    }
}
