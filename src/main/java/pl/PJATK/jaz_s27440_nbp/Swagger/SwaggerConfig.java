package pl.PJATK.jaz_s27440_nbp.Swagger;


import io.swagger.v3.oas.models.media.Schema;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.PJATK.jaz_s27440_nbp.clientQuery.model.ClientQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class SwaggerConfig {
    //I needed to create this because class ClientQuery was not automatically generated.
    @Bean
    public OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return openApi -> {
            openApi.schema("ClientQuery", new Schema<ClientQuery>()
                    .title("ClientQuery")
                    .description("Information about user's queries")
                    .addProperties("id", new Schema<Long>().type("integer").format("int64").description("Unique id of ClientQuery").example(2))
                    .addProperties("currency", new Schema<String>().type("string").description("Currency name").example("USD"))
                    .addProperties("startDate", new Schema<LocalDate>().type("date").description("Interval startDate").example("2024-05-01"))
                    .addProperties("endDate", new Schema<LocalDate>().type("date").description("Interval endDate").example("2024-05-05"))
                    .addProperties("queryDate", new Schema<LocalDateTime>().type("date-time").description("Date of ClientQuery").example("2024-03-25T14:55:00"))
                    .addProperties("meanCourse", new Schema<Double>().type("number").description("Calculated mean of currency from X calendar days").example(3.5))
            );
        };
    }
}
