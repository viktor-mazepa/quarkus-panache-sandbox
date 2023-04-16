package com.mazasoft.quarkus.jdbc.repositories;

import com.mazasoft.quarkus.jdbc.common.Constants;
import com.mazasoft.quarkus.jdbc.model.Artist;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {

    private final DataSource dataSource;
    private final Logger logger;

    @Inject
    public ArtistRepository(DataSource dataSource, Logger logger) {
        this.dataSource = dataSource;
        this.logger = logger;
    }

    public void persist(Artist artist) {
        artist.setId(Math.abs(new Random().nextLong()));
        try (Connection connection = dataSource.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(Constants.INSERT_ARTIST_INTO_DB_SQL);
            ps.setLong(1, artist.getId());
            ps.setString(2, artist.getName());
            ps.setString(3, artist.getBio());
            ps.setTimestamp(4, Timestamp.from(artist.getCreatedDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception during ArtistRepository.findById execution:", e);
        }
    }

    public Artist findById(Long id) {
        Artist artist = new Artist();
        try (Connection connection = dataSource.getConnection();) {
            PreparedStatement ps = connection.prepareStatement(Constants.SELECT_ARTIST_FROM_DB_SQL);
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                artist.setId(resultSet.getLong(1));
                artist.setName(resultSet.getString(2));
                artist.setBio(resultSet.getString(3));
                artist.setCreatedDate(resultSet.getTimestamp(4).toInstant());
            }
        } catch (SQLException e) {
            logger.error("Exception during ArtistRepository.findById execution:", e);
        }
        return artist;
    }
}
