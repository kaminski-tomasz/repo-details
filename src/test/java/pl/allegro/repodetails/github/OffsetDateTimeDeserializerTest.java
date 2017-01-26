package pl.allegro.repodetails.github;

import org.junit.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class OffsetDateTimeDeserializerTest {

    private static final String DATE_TIME_UTC = "2009-12-10T21:41:49Z";

    @Test
    public void shouldGetOffsetDateTime() {
        OffsetDateTimeDeserializer deserializer = new OffsetDateTimeDeserializer();

        OffsetDateTime resultDate = deserializer.getOffsetDateTime(DATE_TIME_UTC);

        assertThat(resultDate).isEqualTo(OffsetDateTime.parse(DATE_TIME_UTC));
    }
}
