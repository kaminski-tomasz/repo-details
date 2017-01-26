package pl.allegro.repodetails.github;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonParser jp, DeserializationContext dc)
            throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String dateString = node.textValue();
        return getOffsetDateTime(dateString);
    }

    OffsetDateTime getOffsetDateTime(String dateString) {
        Instant instant = Instant.parse(dateString);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
