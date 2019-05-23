# Spring Boot Security In Memory Authentication + Thymeleaf

Configure Spring Security to use In Memory Authentication. This is basic example for entering Spring Security and Thymeleaf. In memory means no database required for store users data.

## :rocket: Frameworks & Libs


```bash
Spring Boot 2.1.5
Apache Maven
```

## :rocket: In Memory Authentication

We will create @Configuration class WebSecurityConfig and extend from WebSecurityConfigurerAdapter from Spring security and

This configuration consist from two functions :

### AuthenticationManagerBuilder
For adding user & role configuration

```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{

    User.UserBuilder users = User.withDefaultPasswordEncoder();

    auth.inMemoryAuthentication()
          .withUser(users.username("arda").password("arda").roles("EMPLOYEE"))
          .withUser(users.username("alex").password("alex").roles("ADMIN"));

}

```

### HttpSecurity
For URL & redirect configuration

```java
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

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)