package com.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Blog Api",
                description = "Doing blog crud",
                termsOfService = "T&C",
                contact = @Contact(name = "astik", email = "astikbandekar@gmil.com"),
                license = @License(
                        name = "your licence number"
                ),
                version = "v1"
        )
)
public class OpenApiConfig {
}
