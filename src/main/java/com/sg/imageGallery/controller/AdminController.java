package com.sg.imageGallery.controller;

import com.sg.imageGallery.dao.AdminDaoDB;
import com.sg.imageGallery.dao.ImagesDaoDB;
import com.sg.imageGallery.model.Admin;
import com.sg.imageGallery.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AdminDaoDB adminDaoDB;


    // Get mapping
    @GetMapping("admin") // <-- 1
    public String displayAdminImages(Model model){ // <-- 2
       List<Admin> adminImagesList = adminDaoDB.getAllImages(); // < -- 3 //MIGHT NEED TO CHANGE WITHIN < >
        System.out.println(adminImagesList);
        model.addAttribute("adminsImages", adminImagesList);
        return "admin"; // <-- 4
    }

    //Post mapping
    @PostMapping("addAdminImage")
    public String addImage(HttpServletRequest request){
        String artistInitial = request.getParameter("artistInitial");
        String cityValue = request.getParameter("cityValue");
        String typeValue = request.getParameter("typeValue");
        String price = request.getParameter("price");
        String fileNameValue = request.getParameter("fileNameValue");
        String artistId = request.getParameter("artistId");

        Admin admin = new Admin();
        admin.setArtistInitial(artistInitial);
        admin.setCityValue(cityValue);
        admin.setTypeValue(typeValue);
        admin.setPrice(Integer.parseInt(price));
        admin.setFileNameValue(fileNameValue);
        admin.setArtistId(Integer.parseInt(artistId));

        adminDaoDB.addImage(admin);

        return "redirect:/admin";
    }

    @PostMapping("deleteAdminImage")
    public String deleteImage(HttpServletRequest request){
        String imageId = request.getParameter("imageId");
        adminDaoDB.deleteImageById(Integer.parseInt(imageId));
        return "redirect:/admin";
    }



    //Get mapping for the deletion
//    @GetMapping("deleteImage")
//    public String deleteImage(HttpServletRequest request) {
//        Integer id = Integer.parseInt(request.getParameter("imageId"));
//        //System.out.println("");
//        adminDaoDB.deleteImageById(id);
//        return "redirect:/admin";
//    }

}
