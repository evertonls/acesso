package fortaleza.ce.gov.br.acesso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 * @author everton
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AcessoApplication {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		SpringApplication.run(AcessoApplication.class, args);
	}

}
