package az.developia.movieverse.repository.impl;

import az.developia.movieverse.model.User;
import az.developia.movieverse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            var sql = """
                    select id, username, firstname, lastname, created_at, last_updated_at
                    from users
                    where username = :username
                    """;

            var params = new MapSqlParameterSource()
                    .addValue("username", username);
            var user = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                var userMapped = User.builder()
                        .id(rs.getLong("id"))
                        .firstname(rs.getString("firstname"))
                        .lastname(rs.getString("lastname"))
                        .username(rs.getString("username"))
                        .build();
                userMapped.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                userMapped.setLastUpdatedAt(rs.getTimestamp("last_updated_at").toLocalDateTime());
                return userMapped;
            });
            return Optional.of(user);
        } catch (DataAccessException e) {
            log.error("ActionLog.UserRepositoryImpl.findByAlias.error - ", e);
            return Optional.empty();
        }
    }
}
