package com.sg.imageGallery.controller;

import com.sg.imageGallery.dao.ImagesDaoDB;
import com.sg.imageGallery.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
@Controller
public class ImageController {

    @Autowired // Autowiring the DAOs -- for automatic dependency injection
    ImagesDaoDB imagesDaoDB;

    //A GET mapping that will display the list of Images in our system:  FOR THE LIST PART OF HTML
    // 1. We start with the @GetMapping for images.
    // 2. We have a Model in our method parameters so that we can send data out to a page.
    // 3. We then retrieve the list of Images from the DAO and add it to the Model.
    // 4.Finally, we return teachers, meaning we will need a teachers.html file to push our data to.
    // images >> is the name of html file that is referring to ( images.html)

    @GetMapping("images") // <-- 1
    public String displayImages(Model model){ // <-- 2
        List<Image> imagesList = imagesDaoDB.getAllImages(); // < -- 3
        //System.out.println(imagesList);
        model.addAttribute("images", imagesList);
        return "images"; // <-- 4
    }

    @GetMapping("/imagesOrdered") // <-- 1
    public String displayImagesOrdered(Model model){ // <-- 2
        List<Image> imagesListOrdered = imagesDaoDB.getAllImagesOrderedBy(); // < -- 3
        //System.out.println(imagesList);
        model.addAttribute("images", imagesListOrdered);
        return "images"; // <-- 4
    }

    @GetMapping("/imagesOrderedDesc") // <-- 1
    public String displayImagesOrderedDesc(Model model){ // <-- 2
        List<Image> imagesListOrderedDesc = imagesDaoDB.getAllImagesOrderedByDesc(); // < -- 3
        //System.out.println(imagesList);
        model.addAttribute("images", imagesListOrderedDesc);
        return "images"; // <-- 4
    }

    @PostMapping("/performAction")
    public String performAction(@RequestParam("selectedArtist") String selectedArtist, Model model) {
        if (selectedArtist.equals("MF")) {
            // Perform action for Mahmudul Fakrul
            List<Image> imagesByMF = imagesDaoDB.getImagesByMF();
            model.addAttribute("images", imagesByMF);
        } else if (selectedArtist.equals("SN")) {
            // Perform action for Sadeka Nujhat
            List<Image> imagesBySN = imagesDaoDB.getImagesBySN();
            model.addAttribute("images", imagesBySN);
        }
        return "images";
    }


//    @GetMapping("/viewPage")
//    public String viewPage(@RequestParam("fileName") String fileName) {
//        // Assuming the HTML pages are stored in the "pages" directory under "resources"
//        String htmlPage = fileName + ".html";
//        return "redirect:/" + htmlPage;
//    }

}
