package beans.rest.repository;

import beans.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("create table user(id int, name varchar(50), password varchar(50));");
        jdbcTemplate.execute("insert into user values (1,'ion','db pass ion');");
        jdbcTemplate.execute("insert into user values (2,'gheorghe','db pass gheorghe');");
    }

    public Set<User> getUsers() {
        String sql = "SELECT * FROM user";
        return new HashSet<>(jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class)));
    }

    public User getUser(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    public User createUser(User user) {
        jdbcTemplate.execute("insert into user values (" + user.getId() + ",'" + user.getName() + "','" + user.getPassword() + "');");
        return user;
    }

    public void deleteUser(User user) {
        jdbcTemplate.execute("delete from user where id = " + user.getId());
    }

}
