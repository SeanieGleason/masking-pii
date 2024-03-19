package tech.gleason.maskingpii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaskingPiApplication {
    private static final Logger log =
            LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        SpringApplication.run(MaskingPiApplication.class, args);
        log.info("""
                {
                    "firstName":"Seanie",
                    "phoneNumber":"123 456-7890",
                    "race":"undisclosed",
                    "gender":"female",
                    "birthdate":"03/12/1889",
                    "geodata":"38.897957",
                    "ip":"192.168.0.2",
                    "email":"seanie@gleason.tech"
                }
                """);
    }

}
