package com.mazasoft.quarkus.jdbc.common;

public interface Constants {

    String INSERT_ARTIST_INTO_DB_SQL = "insert into t_artists (id, name, bio,created_date) values (?,?,?,?)";

    String SELECT_ARTIST_FROM_DB_SQL = "select id, name, bio,created_date from t_artists where id = ?";
}
