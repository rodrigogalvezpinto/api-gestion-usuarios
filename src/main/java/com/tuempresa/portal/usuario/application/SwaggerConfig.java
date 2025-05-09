package com.tuempresa.portal.usuario.application;

import com.tuempresa.portal.usuario.adapters.inbound.api.util.ApiDocConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(ApiDocConstants.API_TITLE)
                        .version(ApiDocConstants.API_VERSION)
                        .description(ApiDocConstants.API_DESCRIPTION));
    }
} 