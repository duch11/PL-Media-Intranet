package plmedia.intranet.security;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Jonas holm
 * Web security config er bare en subclass af "WebSecConfigAdapter"
 * vi har en autowired datasource der er "auto config" vha spring
 * og vi har en metode til
 *
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    /**
     * Configure metoden er der hvor vi konfigurerer hvem der må hvad, med hvilke roller
     * fx må alle logge ud
     * man kan også specificerer et custom login view her.
     * */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin().and()
                .logout()
                    .permitAll();
    }

    /**
     * ConfigureGlobal hjælper os til at configurere fx hvilken SQLquery vi vil bruge til at hente
     * et "username" ind fra vores database, og hjælper os til at gøre brug af vores egne tables.
     *
     * Tak til mkyong for opdateret tutorial, kig tidligere commits for den gamle tutorial
     * den var dog udnødvendig og ufleksibel fandt vi ud af.
     * http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
     */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(
                /**Ved fejl kan det være at vi skal have en enabled coloumn i vores user table
                 * Ellers virker det, og vi tjekker op mod user_email.
                 * */
                "select user_email,password, enabled from user where user_email=?")
            .authoritiesByUsernameQuery(
                "select user_email, type from user where user_email=?");

    }

}
