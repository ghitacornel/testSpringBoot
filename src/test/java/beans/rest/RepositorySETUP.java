package beans.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class RepositorySETUP {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("create table user(id int, name varchar(50), password varchar(50));");
        jdbcTemplate.execute("insert into user values (1,'ion','db pass ion');");
        jdbcTemplate.execute("insert into user values (2,'gheorghe','db pass gheorghe');");
    }

}
