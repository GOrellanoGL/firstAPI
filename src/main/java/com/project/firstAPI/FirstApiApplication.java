/**
 * @author Gonzalo Orellano
 * @version 1.0
 * @since 1.0
 */
package com.project.firstAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**Spring Boot Application.**/
@SpringBootApplication
final class FirstApiApplication {

    /**First API aplication.**/
    private FirstApiApplication() {
        //
    }

    /** Main.
     * @param args args.
     **/
    public static void main(final String[] args) {
        SpringApplication.run(FirstApiApplication.class, args);
    }

}
