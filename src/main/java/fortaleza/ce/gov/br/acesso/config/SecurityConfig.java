package fortaleza.ce.gov.br.acesso.config;

/**
 *
 * @author everton
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService users;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requiresChannel().antMatchers("/**").requiresSecure()
                .and()
                .headers()
                .xssProtection().disable()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .antMatchers("/css/**", "/imagens/**", "/js/**", "/fontes/**", "/docs/**").permitAll()
                .antMatchers("/usuarios/**").hasAnyRole("ADMIN", "USER")
                .anyRequest()
                .authenticated()
                .and().formLogin().loginPage("/login/login.xhtml")
                .defaultSuccessUrl("/usuarios/home.xhtml", true)
                .failureUrl("/login/login.xhtml?error=true")
                .and().logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login/login.xhtml")
                .and()
                .exceptionHandling();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
