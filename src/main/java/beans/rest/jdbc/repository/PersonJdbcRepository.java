package beans.rest.jdbc.repository;

import beans.rest.jdbc.model.PersonJdbc;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonJdbcRepository extends JdbcRepository<PersonJdbc, Integer> {

    @Override
    public List<PersonJdbc> findAll() {
        return jdbcTemplate.query("SELECT * FROM person_jdbc", new BeanPropertyRowMapper<>(PersonJdbc.class));
    }

    @Override
    public PersonJdbc findById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person_jdbc WHERE id = ?", new BeanPropertyRowMapper<>(PersonJdbc.class), id);
    }

    public void create(PersonJdbc e) {
        jdbcTemplate.update("INSERT INTO person_jdbc VALUES (?,?,?);", e.getId(), e.getName(), e.getPassword());
    }

    @Override
    public void update(PersonJdbc e) {
        jdbcTemplate.update("UPDATE person_jdbc SET name = ?, password = ? WHERE id = ?;", e.getName(), e.getPassword(), e.getId());
    }

    @Override
    public void remove(PersonJdbc e) {
        jdbcTemplate.update("DELETE FROM person_jdbc WHERE id = ?", e.getId());
    }

    @Override
    public void removeById(Integer id) {
        jdbcTemplate.update("DELETE FROM person_jdbc WHERE id = ?", id);
    }

}
