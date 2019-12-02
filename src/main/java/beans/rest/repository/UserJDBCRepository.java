package beans.rest.repository;

import beans.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserJDBCRepository extends JDBCRepository<User, Integer> {

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("create table user(id int, name varchar(50), password varchar(50));");
        jdbcTemplate.execute("insert into user values (1,'ion','db pass ion');");
        jdbcTemplate.execute("insert into user values (2,'gheorghe','db pass gheorghe');");
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", new BeanPropertyRowMapper<>(User.class), id);
    }

    public void create(User e) {
        jdbcTemplate.update("insert into user values (?,?,?);", e.getId(), e.getName(), e.getPassword());
    }

    @Override
    public void remove(User e) {
        jdbcTemplate.update("delete from user where id = ?", e.getId());
    }

    @Override
    public void removeById(Integer id) {
        jdbcTemplate.update("delete from user where id = ?", id);
    }

}
