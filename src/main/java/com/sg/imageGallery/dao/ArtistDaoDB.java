package com.sg.imageGallery.dao;

import com.sg.imageGallery.model.Artist;
import com.sg.imageGallery.model.Artist;
import com.sg.imageGallery.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArtistDaoDB implements ArtistDao{

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Artist getArtistById(int id) {
        try {
            final String GET_Artist_BY_ID = "SELECT * FROM artists WHERE pk_artistID =?";
            return jdbc.queryForObject(GET_Artist_BY_ID, new ArtistMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }


    @Override
    public List<Artist> getAllArtists(){
        // SQL statement to run
        final String GET_ALL_ARTISTS = "SELECT * FROM artists";

        /*List<Image>images = jdbc.query(GET_ALL_IMAGES,new ImageMapper());
        System.out.println("images"); */

        //the jdbc object is used to execute the SQL Query and retrieve the results
        // the query() method is called on the jdbc object with 2 arguments: SQL Query and instance of ImageMapper
        // ImageMapper() is responsible for mapping the result set from the DB to 'Image' objects
        // query() method returns a list of Image objects, which represents all the images retrieved from the database.
        return jdbc.query(GET_ALL_ARTISTS, new ArtistMapper());
    }


    @Override
    public void updateArtist(Artist artist) {
        final String UPDATE_ARTIST = "UPDATE artists SET firstName = ?, lastName = ?, " +
                "type = ? WHERE pk_artistID = ?";

        jdbc.update(UPDATE_ARTIST,
                artist.getFirstName(),
                artist.getLastName(),
                artist.getType(),
                artist.getArtistId());
    }

    public static final class ArtistMapper implements RowMapper<Artist> {
        @Override  // Overriding the method
        public Artist mapRow(ResultSet rs, int index) throws SQLException {
            Artist artist = new Artist();
            artist.setArtistId(rs.getInt("pk_artistID")); // within the brackets we put the actual table column names
            artist.setFirstName(rs.getString("firstName"));
            artist.setLastName(rs.getString("lastName"));
            artist.setType(rs.getString("type"));
            artist.setBiography(rs.getString("biography"));
            return artist;
        }
    } //End of Class -- AdminMapper

}
