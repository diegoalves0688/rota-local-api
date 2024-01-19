package com.travel.rotalocal.config;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.travel.rotalocal.model.EstadoAtivo;
import com.travel.rotalocal.model.EstadoAtracaoDeserializer;
import com.travel.rotalocal.model.EstadoAtracaoSerializer;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addSerializer(EstadoAtivo.class, new EstadoAtracaoSerializer());
        module.addDeserializer(EstadoAtivo.class, new EstadoAtracaoDeserializer());

        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());

        // Configure date/time format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); // confirmar timezone
        objectMapper.setDateFormat(dateFormat);

        return objectMapper;
    }
}
