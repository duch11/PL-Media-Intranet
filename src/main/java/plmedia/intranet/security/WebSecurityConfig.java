package plmedia.intranet.security;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource datasource;

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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder myAuth) throws Exception {

        /**
         * Tak til https://justinrodenbostel.com/2014/05/30/part-5-integrating-spring-security-with-spring-boot-web/
         * for den gode tutorial på at gøre jdbc authentication mulig.
         */

        //laver instances af userdetailsservice og passwordencoder
        JdbcUserDetailsManager myUserDetailsService = new JdbcUserDetailsManager();
        PasswordEncoder myEncoder = new BCryptPasswordEncoder();

        /*indstiller datasourcen til vores database via datasource (kig i vores MAIN "IntranetApplication"
          det er der den bliver lavet ud fra vores indstillinger i application.properties)
        */
        myUserDetailsService.setDataSource(datasource);

        //hiver fat i vores authentication manager, og tilknytter vores userdetails service og password encoder
        myAuth.userDetailsService(myUserDetailsService).passwordEncoder(myEncoder);

        //vi gør brug af jdbc authentication
        myAuth.jdbcAuthentication().dataSource(datasource);

        if(!myUserDetailsService.userExists("admin")) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            User myUserDetails = new User("autouser", myEncoder.encode("123"),authorities);
            myUserDetailsService.createUser(myUserDetails);
        }


        myAuth
                //.userDetailsService(userDetailsService).and()
                .inMemoryAuthentication()
                    .withUser("user").password("123").roles("USER")
                    .and()
                    .withUser("admin").password("123").roles("ADMIN");
    }

}
