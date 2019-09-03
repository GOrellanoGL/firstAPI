package com.project.firstApiRest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.
        SpringBootServletInitializer;

/**
 * Servlet Initializer.
 **/
public class ServletInitializer extends SpringBootServletInitializer {
    /**
     * Spring Application Builder.
     *
     * @param application application.
     * @return configure.
     **/
    @Override
    protected final SpringApplicationBuilder
    configure(final SpringApplicationBuilder application) {
        return application.sources(FirstApiApplication.class);
    }
}
