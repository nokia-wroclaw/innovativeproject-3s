package com.project.server.sequrity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/h2-console").denyAll() // you can allow the root endpoint ( also will be containing the default /h2-console endpoint ) for all users
                // or put some role restriction on the specific "/h2-console" endpoint to the admin user you are going to be logging in with.
                .antMatchers("/").hasRole("ADMIN")
                .and()
                .csrf().disable() //rest of the configs below according to your needs.
                .headers().frameOptions().disable()
                .and()
                .formLogin();
    }

}