package pl.allegro.repodetails.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OffsetDateTimeDeserializerTest {

    private static final String DATE_TIME_UTC = "2009-12-10T21:41:49Z";

    private JsonParser parser = mock(JsonParser.class);
    private TextNode textNode = mock(TextNode.class);

    @Before
    public void setUp() throws Exception {
        ObjectCodec codec = mock(ObjectCodec.class);
        when(parser.getCodec()).thenReturn(codec);
        when(codec.readTree(any())).thenReturn(textNode);
    }

    @Test
    public void shouldDeserializeOffsetDateTime() throws Exception {

        when(textNode.textValue()).thenReturn(DATE_TIME_UTC);
        OffsetDateTimeDeserializer deserializer = new OffsetDateTimeDeserializer();

        OffsetDateTime resultDate = deserializer.deserialize(parser,
                mock(DeserializationContext.class));

        assertThat(resultDate).isEqualTo(OffsetDateTime.parse(DATE_TIME_UTC));
    }
}
