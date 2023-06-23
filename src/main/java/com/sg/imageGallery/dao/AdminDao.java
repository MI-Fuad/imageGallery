package com.sg.imageGallery.dao;

import com.sg.imageGallery.model.Admin;
import com.sg.imageGallery.model.Image;

import java.util.List;

public interface AdminDao {

    Admin getImageById(int id);
    List<Admin> getAllImages();
    Admin addImage(Admin admin);
    void deleteImageById(int id);
}
