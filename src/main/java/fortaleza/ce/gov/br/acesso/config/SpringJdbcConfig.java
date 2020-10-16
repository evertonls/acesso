/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fortaleza.ce.gov.br.acesso.config;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author everton
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("fortaleza.ce.gov.br.acesso.config")
public class SpringJdbcConfig {
    
    @Primary
    @Bean(name = "acessodb")
    public DataSource postgresqlDataSource() {
        DataSourceBuilder<?> dataSource = DataSourceBuilder.create();
        dataSource.driverClassName("org.postgresql.Driver");
        dataSource.url("jdbc:postgresql://localhost:5432/acessodb");
        dataSource.username("acesso_user");
        dataSource.password("123456789");

        return dataSource.build();
    }
}
