package com.example.devtoolindex.serializer;

import com.example.devtoolindex.config.Param;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hongkailiu on 2016-04-16.
 */
@Slf4j public class CustomDateDeserializer extends JsonDeserializer<Date> {
    @Override public Date deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        ObjectCodec oc = p.getCodec();
        JsonNode node = oc.readTree(p);
        String msDateString = node.asText();

        if (StringUtils.isBlank(msDateString)) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(Param.DATE_PATTERN);
        Date date = null;
        try {
            date = format.parse(msDateString);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return date;
    }
}
