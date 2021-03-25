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
        jdbcTemplate.update("INSERT INTO person_jdbc(id,name,pass) VALUES (?,?,?);", e.getId(), e.getName(), e.getPass());
    }

    @Override
    public void update(PersonJdbc e) {
        jdbcTemplate.update("UPDATE person_jdbc SET name = ?, pass = ? WHERE id = ?;", e.getName(), e.getPass(), e.getId());
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
