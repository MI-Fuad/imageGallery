package com.sg.imageGallery.dao;

import com.sg.imageGallery.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/*
 * This is where I am implementing the functionalities of the Interface
 * Here I am implementing the abstract class
 * */

/*The ~= @Repository =~ annotation below, will be DETECTED by Spring
and Spring will CREATE a bean for this class within the app
this bean can be used for data access operations within the app
Typically used on classes that interact with a database
*  */

@Repository
public class ImagesDaoDB implements ImagesDao{

    //By using @Autowired on the jdbc field, I am instructing Spring to automatically inject an instance
    // f JdbcTemplate into that field when creating an instance of the class containing this field.
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Image> getAllImagesOrderedBy(){
        final String GET_ALL_IMAGES_Ordered = "SELECT * FROM images ORDER BY price";
        return jdbc.query(GET_ALL_IMAGES_Ordered, new ImageMapper());
    }
    @Override
    public List<Image>getAllImagesOrderedByDesc(){
        final String GET_ALL_IMAGES_DESC = "SELECT * FROM images ORDER BY price DESC";
        return jdbc.query(GET_ALL_IMAGES_DESC, new ImageMapper());
    }
    @Override
    public List<Image> getImagesByMF(){
        final String GET_IMAGES_BY_MF = "SELECT * FROM images WHERE artist = 'MF'";
        return jdbc.query(GET_IMAGES_BY_MF, new ImageMapper());
    }

    @Override
    public List<Image> getImagesBySN(){
        final String GET_IMAGES_BY_SN = "SELECT * FROM images WHERE artist = 'SN'";
        return jdbc.query(GET_IMAGES_BY_SN, new ImageMapper());
    }

    // Method 1
    @Override
    public Image getImageById(int id){

        try{
            // Essentially the SQL QUERY String
            final String GET_IMAGE_BY_ID = "SELECT * FROM images WHERE pk_imageID =?";

            //The jdbc.queryForObject method is called below to execute the SQL query
            //and then MAP the result to a 'Image' object
            // the 3 arguments - 1. SQL QUERY
            // 2. an instance for the TeacherMapper class (defined below)
            // 3. id parameter used to replace the placeholder ('?') in the SQL Query
            return jdbc.queryForObject(GET_IMAGE_BY_ID, new ImageMapper(),id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    // Method 2
    @Override
    public List<Image> getAllImages(){
        // SQL statement to run
        final String GET_ALL_IMAGES = "SELECT * FROM images";

        /*List<Image>images = jdbc.query(GET_ALL_IMAGES,new ImageMapper());
        System.out.println("images"); */

        //the jdbc object is used to execute the SQL Query and retrieve the results
        // the query() method is called on the jdbc object with 2 arguments: SQL Query and instance of ImageMapper
        // ImageMapper() is responsible for mapping the result set from the DB to 'Image' objects
        // query() method returns a list of Image objects, which represents all the images retrieved from the database.
        return jdbc.query(GET_ALL_IMAGES, new ImageMapper());
    }


    public static final class ImageMapper implements RowMapper<Image> {

        /*ImageMapper class:

        The TeacherMapper class is a static nested class defined within the same class as the getImageById method.
        Being a static nested class, it can be accessed without creating an instance of the enclosing class.
        The ImageMapper class implements the RowMapper<Image> interface, which is provided by the Spring JDBC framework.
        This interface defines a single method mapRow that is responsible for mapping a row from the ResultSet to an Image object.
        Inside the mapRow method, a new Image object is created, and the corresponding fields (imageId, artistInitial, cityValue, typeValue, price, fileNameValue, artistId)
        are extracted from the ResultSet using the appropriate methods (getInt, getString, etc.).
        The extracted values are then set on the Image object.
        Finally, the Image object is returned.*/

        @Override  // Overriding the method
        public Image mapRow(ResultSet rs, int index) throws SQLException {
            Image image = new Image();
            image.setImageId(rs.getInt("pk_imageID")); // within the brackets we put the actual table column names
            image.setArtistInitial(rs.getString("artist"));
            image.setCityValue(rs.getString("city"));
            image.setTypeValue(rs.getString("type"));
            image.setPrice(rs.getInt("price"));
            image.setFileNameValue(rs.getString("fileName"));
            image.setArtistId(rs.getInt("artistID"));

            return image;
        }
    } //End of Class -- ImageMapper

} // End of Class -- ImagesDaoDB
