package pl.allegro.repodetails.datetime;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeUtils {

    private static final String DATE_TIME_PATTERN = "dd MMMM uuuu, HH:mm:ss O";

    private DateTimeUtils() {
        throw new AssertionError();
    }

    public static String formatDateTime(OffsetDateTime dateTime, Locale locale) {
        return DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, locale)
                .format(dateTime);
    }

    public static OffsetDateTime getOffsetDateTime(String dateString) {
        Instant instant = Instant.parse(dateString);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
