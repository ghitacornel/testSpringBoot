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

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE id = ?", new BeanPropertyRowMapper<>(User.class), id);
    }

    public void create(User e) {
        jdbcTemplate.update("INSERT INTO user VALUES (?,?,?);", e.getId(), e.getName(), e.getPassword());
    }

    @Override
    public void remove(User e) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", e.getId());
    }

    @Override
    public void removeById(Integer id) {
        jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
    }

}
