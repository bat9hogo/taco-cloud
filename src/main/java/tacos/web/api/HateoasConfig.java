package tacos.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.LinkBuilder;

@Configuration
public class HateoasConfig {

    @Bean
    public EntityLinks entityLinks() {
        return new EntityLinks() {
            @Override
            public boolean supports(Class<?> aClass) {
                return false;
            }

            @Override
            public LinkBuilder linkFor(Class<?> type) {
                return null;
            }

            @Override
            public LinkBuilder linkFor(Class<?> type, Object... parameters) {
                return null;
            }

            @Override
            public LinkBuilder linkForItemResource(Class<?> type, Object id) {
                return null;
            }

            @Override
            public Link linkToCollectionResource(Class<?> type) {
                return null;
            }

            @Override
            public Link linkToItemResource(Class<?> type, Object id) {
                return null;
            }
        };
    }
}
