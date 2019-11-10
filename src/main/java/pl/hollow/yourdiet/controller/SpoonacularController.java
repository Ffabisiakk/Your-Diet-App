package pl.hollow.yourdiet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hollow.yourdiet.client.SpoonacularClient;

@RestController
@RequestMapping("spoonacular")
public class SpoonacularController {

    @Autowired
    private SpoonacularClient spoonacularClient;

}
