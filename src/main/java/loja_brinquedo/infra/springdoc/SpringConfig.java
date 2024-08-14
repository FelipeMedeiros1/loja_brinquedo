package loja_brinquedo.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public OpenAPI configuracao() {

        return new OpenAPI().info(new Info()
                .title("loja de brinquedo ")
                .description("API Rest da aplicação loja de brinquedo," +
                        " contendo as funcionalidades de CRUD de usuário, brinquedo," +
                        " além de adicionar e remover itens no carrinho e " +
                        " finalizar compra aplicando a regra de negócio se necessário. ")
                .contact(new Contact()
                        .name("Felipe medeiros")
                        .email("medeirosjava83@gmail.com")));
    }
}
