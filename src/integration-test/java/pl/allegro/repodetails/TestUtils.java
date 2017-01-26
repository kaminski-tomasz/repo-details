package pl.allegro.repodetails;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;

public class TestUtils {

    private TestUtils() {
        throw new AssertionError();
    }

    public static String loadFileContent(String resourceName) throws IOException {
        return Resources.toString(Resources.getResource(resourceName), Charsets.UTF_8);
    }
}
