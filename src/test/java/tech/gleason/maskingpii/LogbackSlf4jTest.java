package tech.gleason.maskingpii;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class LogbackSlf4jTest {

    private static final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private static final PrintStream standardOut = System.out;

    // Define Slf4j root logger
    private final Logger log =
            LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @BeforeAll
    static void beforeAll() {
        // Divert System out to output stream for testing
        System.setOut(new PrintStream(out));
    }

    @ParameterizedTest
    @CsvSource(textBlock = """
            {"firstName":"Seanie"},{\\"firstName\\":\\"*******\\"}
            {"phoneNumber":"123 456-7890"},{\\"phoneNumber\\":\\"*******\\"}
            {"race":"undisclosed"},{\\"race\\":\\"*******\\"}
            {"gender":"female"},{\\"gender\\":\\"*******\\"}
            {"birthdate":"03/12/1889"},{\\"birthdate\\":\\"*******\\"}
            {"geodata":"38.897957"},{\\"geodata\\":\\"*******\\"}
            {"ip":"192.168.0.2"},{\\"ip\\":\\"***.***.*.*\\"}
            {"email":"seanie@gleason.tech"},{\\"email\\":\\"s******@g******.t**\\"}
            """)
    void testLogbackMaskMatch(String string, String expected) {

        log.info(string);
        assertThat(out.toString()).contains(expected);
    }

    @AfterAll
    static void afterAll() {
        // Restore System out
        System.setOut(standardOut);
    }
}
