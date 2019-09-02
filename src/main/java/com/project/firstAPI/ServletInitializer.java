package com.project.firstAPI;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    /**Spring Application Builder.**/
    @Override
    protected SpringApplicationBuilder
        configure(final SpringApplicationBuilder application) {
            return application.sources(FirstApiApplication.class);
    }
}
