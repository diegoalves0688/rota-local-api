package com.travel.rotalocal.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.travel.rotalocal.model.EstadoAtracao;
import com.travel.rotalocal.model.EstadoAtracaoDeserializer;
import com.travel.rotalocal.model.EstadoAtracaoSerializer;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addSerializer(EstadoAtracao.class, new EstadoAtracaoSerializer());
        module.addDeserializer(EstadoAtracao.class, new EstadoAtracaoDeserializer());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(dateFormat);

        return objectMapper;
    }
}
