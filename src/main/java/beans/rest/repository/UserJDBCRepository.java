package beans.rest.repository;

import beans.rest.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void update(User e) {
        jdbcTemplate.update("UPDATE user SET name = ?, password = ? WHERE id = ?;", e.getName(), e.getPassword(), e.getId());
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
