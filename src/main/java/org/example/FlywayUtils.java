package org.example;

import org.flywaydb.core.Flyway;

import java.io.File;

public class FlywayUtils {
    public static void initFlyWay() {
        try {
            Flyway flyway = Flyway
                    .configure()
                    .locations("classpath:db/migration")
                    .dataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "12345")
                    .load();
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
