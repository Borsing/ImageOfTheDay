package imageday.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ConfigurationService configurationService ;

    @Autowired
    RestSecurityConfig(ConfigurationService configurationService){
        this.configurationService = configurationService ;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/photos/**").permitAll()
                .anyRequest().hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername(configurationService.getAdminUsername())
                        .password(encoder().encode(
                                configurationService.getAdminPassword()))
                        .roles("ADMIN")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
