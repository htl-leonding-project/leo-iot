package at.htl.entity;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Leo-IOT API",
                description = "get the various datasets",
                version = "1.0",
                contact = @Contact(name = "Leo-IOT Team", url = "https://htl-leonding-project.github.io/leo-iot/")
        ),
        externalDocs = @ExternalDocumentation(url = "https://htl-leonding-project.github.io/leo-iot/documentation"),
        tags = {
                @Tag(name = "api", description = "Leo-IOT API")
        }
)

public class NumberMicroservice extends  Application{
}
