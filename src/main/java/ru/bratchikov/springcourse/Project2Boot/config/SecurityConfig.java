package ru.bratchikov.springcourse.Project2Boot.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.bratchikov.springcourse.Project2Boot.services.PersonDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PersonDetailsService personDetailsService;

    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
//                .permitAll()
//                 //.antMatchers("/admin").hasRole("ADMIN") //Только для админа
//
//                .antMatchers("/", "/callback", "/login**", "/jpg/**", "/error**", "resources/static/**")
//                .permitAll()
//                .antMatchers("/auth/login","/auth/registration", "/error", "/hello", "/css/style.css","/auth/test","/css/styletest.css",
//                        "/static/jpg/pirate.jpg","/books/2","/comments","../src/main/resources/static/svg/icons8-amazon-alexa-logo-100.svg").permitAll() //Все могут это получать
//                .anyRequest().hasAnyRole("USER","ADMIN") //только для юзера и админа
//                .and()
//                .formLogin().loginPage("/auth/login")
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/hello", true)
//                .failureUrl("/auth/login?error")
//                .and()
//                .logout().logoutUrl("/logout")
//                .logoutSuccessUrl("/auth/login");

        //permit all
        http.authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll();


    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { //Настроив шифрование паролей
        return new BCryptPasswordEncoder();
    }
}
