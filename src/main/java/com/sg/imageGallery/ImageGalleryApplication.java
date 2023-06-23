package com.sg.imageGallery;
import com.sg.imageGallery.dao.ImagesDaoDB;
import com.sg.imageGallery.controller.ImageController;
import com.sg.imageGallery.controller.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageGalleryApplication.class, args);

	}

}
