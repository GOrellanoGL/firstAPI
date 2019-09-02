package com.project.firstAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
final class FirstApiApplication {

    private FirstApiApplication() {
        //
    }

    /** Main.
     * @param args args.
     */
    public static void main(final String[] args) {
        SpringApplication.run(FirstApiApplication.class, args);
    }

}
