package com.sg.imageGallery.dao;

import com.sg.imageGallery.model.Image;

import java.util.List;

/* The ImageDao Interface:
    Defining the very bare functionality of the Image object.
    Forcing me to IMPLEMENT MY INTERPRETATION of the objects FUNCTIONALITY

    What are the CRUD methods that I want for the images?
    - . To be able to get the images by id
    - . To view all the images as a LIST
    - . Add an image
    - . Delete an image


    - To be able to get the images by Location, - later
    - Not
*/
public interface ImagesDao {

    Image getImageById(int id);
    List<Image> getAllImages();

    List<Image> getAllImagesOrderedBy();
    List<Image> getAllImagesOrderedByDesc();

    List<Image> getImagesByMF();
    List<Image> getImagesBySN();


    //May need ti add List<Artists> getImagesForArtist(Artists artists) when trying to get the  images by artist
}
