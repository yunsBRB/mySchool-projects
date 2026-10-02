package be.technofutur.moonname.api.configss;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    // Authorize de swagger c pour coller le jwt plus facilement
    @Bean public OpenAPI moonApi() {
        return new OpenAPI()
                .info(new Info().title("MoonName API").version("1.0"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
