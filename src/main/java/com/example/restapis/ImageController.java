package com.example.restapis;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

/*
Frontend get some id & corresponding to that id return a particular Image.
 */

 /*
        Rest Template is used to make an api call to a different service,
        or you can say to invoke another api and get it's response.

        so this application right here will be acting like a client,
        & service lorem pics would be acting as a server and we are getting Image from it.
        So, one entity can be a client ans server at the same time. but for different-different entity.

         Now RestTemplate will be used to make an api call at lorem pics and get some response,
         from this website back and what response we get can return it to our client (that is mobile)

          Difference between url & uri???

          Uri - any generic resource you want to access
          Url - specific to your http protocol (specific to http location from where you want data)


  */

@RestController
public class ImageController {
    @GetMapping(value = "/image",produces = {MediaType.IMAGE_PNG_VALUE})
    // This automatically adds it into response headers and we don't have to do extra work.
    public byte[] getImage(@RequestParam("id") int id,
                           @RequestParam(value = "length",required = false,defaultValue="200") int length,
                           @RequestParam(value = "breadth",required = false,defaultValue ="300") int breadth){
        String url = "https://picsum.photos/id/" + id + "/" + length + "/" + breadth;
        RestTemplate restTemplate = new RestTemplate();
        byte[] image = restTemplate.getForObject(url, byte[].class);
        return image;

        // http:///localhost:8080/image?id=1&length=100&breadth=200
    }
}
