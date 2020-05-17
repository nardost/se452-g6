package edu.depaul.g6.ui.config;

import edu.depaul.g6.commons.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 *
 * @author Christian Kleinvehn
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private G6UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/", "/subscribe", "/password-reset").permitAll()
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/*").hasRole("USER") // every other page
            .and()

        .formLogin()
            .loginPage("/")
            .successHandler(new G6AuthenticationSuccessHandler()) // redirects ADMINs to /admin/, USERs to /
            .and()

        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // doesn't need to be mapped w/ a controller
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .logoutSuccessUrl("/?logout")
            .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers("/styles/**", "/webjars/**"); // allow these pages to pass through our filter
    }
}
