package beans.rest.jdbc.repository;

import beans.rest.jdbc.model.PersonJDBC;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJDBCRepository extends JDBCRepository<PersonJDBC, Integer> {

    @Override
    public List<PersonJDBC> findAll() {
        return jdbcTemplate.query("SELECT * FROM person_jdbc", new BeanPropertyRowMapper<>(PersonJDBC.class));
    }

    @Override
    public PersonJDBC findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person_jdbc WHERE id = ?", new BeanPropertyRowMapper<>(PersonJDBC.class), id);
    }

    public void create(PersonJDBC e) {
        jdbcTemplate.update("INSERT INTO person_jdbc VALUES (?,?,?);", e.getId(), e.getName(), e.getPassword());
    }

    @Override
    public void update(PersonJDBC e) {
        jdbcTemplate.update("UPDATE person_jdbc SET name = ?, password = ? WHERE id = ?;", e.getName(), e.getPassword(), e.getId());
    }

    @Override
    public void remove(PersonJDBC e) {
        jdbcTemplate.update("DELETE FROM person_jdbc WHERE id = ?", e.getId());
    }

    @Override
    public void removeById(Integer id) {
        jdbcTemplate.update("DELETE FROM person_jdbc WHERE id = ?", id);
    }

}
