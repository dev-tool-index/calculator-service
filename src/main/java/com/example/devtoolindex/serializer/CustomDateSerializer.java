package com.example.devtoolindex.serializer;

import com.example.devtoolindex.config.Param;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongkailiu on 2016-04-16.
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    @Override public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat(Param.DATE_PATTERN);
        String formattedDate = formatter.format(value);
        gen.writeString(formattedDate);
    }
}
