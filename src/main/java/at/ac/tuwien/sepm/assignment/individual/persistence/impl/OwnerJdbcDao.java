package at.ac.tuwien.sepm.assignment.individual.persistence.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerJdbcDao implements OwnerDao {

    private static final String TABLE_NAME = "Owner";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public OwnerJdbcDao(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Owner findOneById(Long id) {
        LOGGER.trace("Get owner with id {}", id);
        final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        List<Owner> owners = jdbcTemplate.query(sql, new Object[] { id }, this::mapRow);

        if (owners.isEmpty()) throw new NotFoundException("Could not find owner with id " + id);

        return owners.get(0);
    }

    @Override
    public Owner insertOwner(Owner o) {
        LOGGER.trace("Insert new Owner with id {}", Owner.getOwnerCount());

        final String sql = "INSERT INTO owners (id, name, created_at, updated_at) VALUES ("+Owner.getOwnerCount()+ ", '" + o.getName()+"', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP())";
        Owner.setOwnerCount(Owner.getOwnerCount()+1);
        jdbcTemplate.update(sql);

        return o;
    }

    private Owner mapRow(ResultSet resultSet, int i) throws SQLException {
        final Owner owner = new Owner();
        owner.setId(resultSet.getLong("id"));
        owner.setName(resultSet.getString("name"));
        owner.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        owner.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return owner;
    }

}
