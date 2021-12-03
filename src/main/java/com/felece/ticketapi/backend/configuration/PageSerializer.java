package com.felece.ticketapi.backend.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;

//@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(final Page<?> objects, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("content");
        serializerProvider.defaultSerializeValue(objects.getContent(), jsonGenerator);
        jsonGenerator.writeObjectField("pageable", objects.getPageable());
        jsonGenerator.writeBooleanField("last", objects.isLast());
        jsonGenerator.writeNumberField("totalPages",objects.getTotalPages());
        jsonGenerator.writeNumberField("totalElements", objects.getTotalElements());
        jsonGenerator.writeNumberField("size", objects.getSize());
        jsonGenerator.writeNumberField("number", objects.getNumber());
        jsonGenerator.writeObjectField("sort", objects.getSort());
        jsonGenerator.writeNumberField("numberOfElements", objects.getNumberOfElements());
        jsonGenerator.writeBooleanField("first", objects.isFirst());
        jsonGenerator.writeBooleanField("empty", objects.isEmpty());
        jsonGenerator.writeEndObject();
    }
}
