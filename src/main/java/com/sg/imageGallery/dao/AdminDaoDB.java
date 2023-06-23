package com.sg.imageGallery.dao;

import com.sg.imageGallery.model.Admin;
import com.sg.imageGallery.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminDaoDB implements AdminDao {

    @Autowired
    JdbcTemplate jdbc;


    // BECAUSE AdminDao Interface is extending the ImagesDao
    // Here on AdminDaoDB I need to make sure I implement
    // all functionalities from ImagesDao Interface
    // as well as AdminDao Interface functionalities


    // Method implementation from ImagesDao
    @Override
    public Admin getImageById(int id){

        try{
            // Essentially the SQL QUERY String
            final String GET_IMAGE_BY_ID = "SELECT * FROM images WHERE pk_imageID =?";

            //The jdbc.queryForObject method is called below to execute the SQL query
            //and then MAP the result to a 'Image' object
            // the 3 arguments - 1. SQL QUERY
            // 2. an instance for the TeacherMapper class (defined below)
            // 3. id parameter used to replace the placeholder ('?') in the SQL Query
            return jdbc.queryForObject(GET_IMAGE_BY_ID, new AdminMapper(),id);
        }catch(DataAccessException ex){
            return null;
        }
    }


    // Method implementation from ImagesDao
    @Override
    public List<Admin> getAllImages(){
        // SQL statement to run
        final String GET_ALL_IMAGES = "SELECT * FROM images";

        /*List<Image>images = jdbc.query(GET_ALL_IMAGES,new ImageMapper());
        System.out.println("images"); */

        //the jdbc object is used to execute the SQL Query and retrieve the results
        // the query() method is called on the jdbc object with 2 arguments: SQL Query and instance of ImageMapper
        // AdminMapper() is responsible for mapping the result set from the DB to 'Image' objects
        // query() method returns a list of Image objects, which represents all the images retrieved from the database.
        return jdbc.query(GET_ALL_IMAGES, new AdminMapper());
    }


    // Additional Method implementation from AdminDaoDB
    @Override
    @Transactional
    // 1. The method addImage takes an Image object as a parameter and returns an Image object.
    public Admin addImage(Admin admin){
        //2. SQL statement to run
        // INSERT Statement that inserts a new row into the images table
        //                                             -- within the brackets we need to put column names of the actual DB
        final String INSERT_IMAGE = "INSERT INTO images(artist,city,type,price,fileName,artistID)"+
                " VALUES(?,?,?,?,?,?)"; // these placeholders are replaced below with the jdbc.update method

        //3. Data Insertion: The values of the placeholders are passed as argument to the jdbc.update method
        // extracted from the 'image' parameter of the addImage method, using the getters method from the model Image.
        try {

            jdbc.update(INSERT_IMAGE,
                    admin.getArtistInitial(),
                    admin.getCityValue(),
                    admin.getTypeValue(),
                    admin.getPrice(),
                    admin.getFileNameValue(),
                    admin.getArtistId());

            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
            admin.setImageId(newId);

        }catch(DataAccessException e){
            e.printStackTrace();
        }
        return admin;
    }

    // Additional Method implementation from AdminDaoDB
    @Override
    @Transactional
    public void deleteImageById(int id){
        final String DELETE_IMAGE = "DELETE FROM images WHERE pk_imageID = ?";
        jdbc.update(DELETE_IMAGE,id);
    }

    public static final class AdminMapper implements RowMapper<Admin> {
        @Override  // Overriding the method
        public Admin mapRow(ResultSet rs, int index) throws SQLException {
            Admin admin = new Admin();
            admin.setImageId(rs.getInt("pk_imageID")); // within the brackets we put the actual table column names
            admin.setArtistInitial(rs.getString("artist"));
            admin.setCityValue(rs.getString("city"));
            admin.setTypeValue(rs.getString("type"));
            admin.setPrice(rs.getInt("price"));
            admin.setFileNameValue(rs.getString("fileName"));
            admin.setArtistId(rs.getInt("artistID"));

            return admin;
        }
    } //End of Class -- AdminMapper

}// Enf of Class -- AdminDaoDB
