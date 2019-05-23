package com.alexanderarda.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    // IN MEMORY AUTHENTICATION

    // AuthenticationManagerBuilder
    // for user & role configuration --------------------------------------------

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("arda").password("arda").roles("EMPLOYEE"))
                .withUser(users.username("alex").password("alex").roles("ADMIN"));

    }

    // HttpSecurity
    // for URL & redirect configuration ------------------------------------------

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()

            // Restrict path for all sub-directories - only for role ADMIN that can access URL with /admin/xxxx
                .antMatchers("/admin/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()

            // Allowed url (eg. login page)
                .formLogin()

            // URL after auth success
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
                .and()

            // Page for custom access denied
                .exceptionHandling().accessDeniedPage("/access-denied");

    }

}
