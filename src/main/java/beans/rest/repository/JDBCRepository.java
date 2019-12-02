package beans.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class JDBCRepository<Entity, Id> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public abstract List<Entity> getAll();

    public Entity getById(Id id) {
        Entity entity = findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exists");
        }
        return entity;
    }

    public abstract Entity findById(Id id);

    public abstract void create(Entity e);

    public abstract void remove(Entity e);

    public abstract void removeById(Id id);

}
