package com.sg.imageGallery.controller;


import com.sg.imageGallery.dao.ArtistDaoDB;
import com.sg.imageGallery.model.Artist;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    ArtistDaoDB artistDaoDB;

    @GetMapping("artist")
    public String displayArtists(Model model){
        List<Artist> artistsList = artistDaoDB.getAllArtists();
        System.out.println(artistsList);

        model.addAttribute("artists",artistsList);
        return "artist";
    }

    @GetMapping("editArtist")
    public String editArtist(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Artist artist = artistDaoDB.getArtistById(id);

        model.addAttribute("artist", artist);
        return "editArtist";
    }


    /*This is a POST mapping for editArtist.
    We only need to take in the HttpServletRequest so we can get the data out of the form.
    We start by getting the hidden ID and pulling in the original version of the object.
    We then get all the new data out of the form and set it into the Artist object.
    Once we have all the data, we make a call to our DAO update method.
    We can then redirect back to the main Artist page.*/

    @PostMapping("editArtist")
    public String performEditArtist(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        //Integer id = Integer.valueOf(request.getParameter("id"));
        Artist artist = artistDaoDB.getArtistById(id);

        artist.setFirstName(request.getParameter("firstName"));
        artist.setLastName(request.getParameter("lastName"));
        artist.setType(request.getParameter("type"));
        artist.setBiography(request.getParameter("biography"));

        artistDaoDB.updateArtist(artist);

        return "redirect:/artist";
    }



    /*@PostMapping("editArtist")
    public String performEditArtist(HttpServletRequest request) {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            // Handle the case when id is not provided in the request
            // For example, you can show an error message or redirect to an error page
            return "error";
        }
        int id = Integer.parseInt(idParam);
        Artist artist = artistDaoDB.getArtistById(id);

        // Rest of your code...

        return "redirect:/artist";
    }*/

}
