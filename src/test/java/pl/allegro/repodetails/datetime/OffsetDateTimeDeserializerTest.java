package pl.allegro.repodetails.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class OffsetDateTimeDeserializerTest {

    private static final String DATE_TIME_UTC = "2009-12-10T21:41:49Z";

    private ObjectMapper mapper;
    private OffsetDateTimeDeserializer deserializer;

    @Before
    public void setup() {
        mapper = new ObjectMapper();
        deserializer = new OffsetDateTimeDeserializer();
    }

    @Test
    public void shouldDeserializeOffsetDateTime() throws Exception {
        String json = String.format("{\"value\":\"%s\"}", DATE_TIME_UTC);

        OffsetDateTime resultDate = deserializeOffsetDateTime(json);

        assertThat(resultDate).isEqualTo(OffsetDateTime.parse(DATE_TIME_UTC));
    }

    private OffsetDateTime deserializeOffsetDateTime(String json)
            throws IOException {
        InputStream stream = new ByteArrayInputStream(
                json.getBytes(StandardCharsets.UTF_8));
        JsonParser parser = mapper.getFactory().createParser(stream);
        DeserializationContext context = mapper.getDeserializationContext();
        parser.nextToken();
        parser.nextToken();
        parser.nextToken();
        return deserializer.deserialize(parser, context);
    }
}
