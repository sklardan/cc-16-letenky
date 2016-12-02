package cz.codecamp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Created by jakubbares on 12/1/16.
 */

@Configuration
public class SpringTemplateEngineBean {
//    @Bean
//    public SpringTemplateEngine templateEngine(MessageSource messageSource, ServletContextTemplateResolver templateResolver) {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//        engine.setMessageSource(messageSource);
//        return engine;
//    }
}
