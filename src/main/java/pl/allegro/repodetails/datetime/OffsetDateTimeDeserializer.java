package pl.allegro.repodetails.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.OffsetDateTime;

public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    @Override
    public OffsetDateTime deserialize(JsonParser parser, DeserializationContext context)
            throws IOException {
        ObjectCodec codec = parser.getCodec();
        TextNode node = codec.readTree(parser);
        String dateString = node.textValue();
        return DateTimeUtils.getOffsetDateTime(dateString);
    }
}
