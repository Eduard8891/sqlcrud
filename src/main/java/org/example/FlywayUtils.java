package org.example;

import org.flywaydb.core.Flyway;

public class FlywayUtils {
    public static void initFlyWay() {
        try {
            Flyway flyway = Flyway.configure().load();
            flyway.migrate();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
