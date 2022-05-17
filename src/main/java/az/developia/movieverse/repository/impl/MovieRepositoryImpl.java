package az.developia.movieverse.repository.impl;

import az.developia.movieverse.model.Movie;
import az.developia.movieverse.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MovieRepositoryImpl implements MovieRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Movie save(Movie entity) {
        var sql = """
                insert into movies(title, plot, key_id, year, runtime_mins)\s
                values (:title, :plot, :key, :year, :runtime_mins)
                   """;
        var params = new MapSqlParameterSource()
                .addValue("title", entity.getTitle())
                .addValue("plot", entity.getPlot())
                .addValue("key_id", entity.getKeyId())
                .addValue("year", entity.getYear())
                .addValue("runtime_mins", entity.getRuntimeMins());
        var keyHolder = new GeneratedKeyHolder();
        var updated = jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
        log.debug("insert count: {}", updated);

        entity.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        return entity;
    }

    @Override
    public Movie update(Movie entity) {
        return null;
    }

    @Override
    public Optional<Movie> findById(Long aLong) {
        try {
            var sql = """
                    select id, key_id, title, plot, year, runtime_mins
                    from movies
                    where id = :id
                    """;
            var params = new MapSqlParameterSource()
                    .addValue("id", aLong);


            var movie = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                var mappedMovie = Movie.builder()
                        .id(rs.getLong("id"))
                        .keyId(rs.getString("key_id"))
                        .title(rs.getString("title"))
                        .plot(rs.getString("plot"))
                        .year(rs.getShort("year"))
                        .runtimeMins(rs.getLong("runtime_mins"))
                        .build();
                return mappedMovie;
            });
            return Optional.of(movie);

        } catch (DataAccessException e){
            log.error("ActionLog.BookRepositoryImpl.findById.error -  ", e);
            return Optional.empty();
        }

    }

    @Override
    public void deleteById(Long aLong) {

    }


    @Override
    public Optional<Movie> findByKey(String keyId) {
        log.info("ActionLog.MovieRepositoryImpl.findByKey.start");
        try {
            var sql = """
                    select id, key_id, title, plot, year, runtime_mins
                    from movies
                    where key_id = :key_id 
                    """;
            var params = new MapSqlParameterSource()
                    .addValue("keyId", keyId);


            var movie = jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> {
                var mappedMovie = Movie.builder()
                        .id(rs.getLong("id"))
                        .keyId(rs.getString("key_id"))
                        .title(rs.getString("title"))
                        .plot(rs.getString("plot"))
                        .year(rs.getShort("year"))
                        .runtimeMins(rs.getLong("runtime_mins"))
                        .build();
                return mappedMovie;
            });
            log.info("ActionLog.MovieRepositoryImpl.findByKey.end");
            return Optional.of(movie);
        } catch (DataAccessException e){
            log.error("ActionLog.BookRepositoryImpl.findById.error -  ", e);
            return Optional.empty();
        }

    }
}
