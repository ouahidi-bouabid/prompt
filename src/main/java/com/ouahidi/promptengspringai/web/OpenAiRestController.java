package com.ouahidi.promptengspringai.web;

import com.ouahidi.promptengspringai.prompteng.Prompt;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAiRestController {


    //@Value("$(spring.ai.openai.api-key)")
    //private String aipKey;

    private Prompt promptGen;
    public OpenAiRestController(Prompt promptGen) {
        this.promptGen = promptGen;
    }

@GetMapping(value="/vente/{promptuser}", produces =  MediaType.TEXT_MARKDOWN_VALUE)
    public String pedagogique(@PathVariable  String promptuser){
       // String promptuser = " Marque PC portable HP, RAM 4Go, Processeur i5, DD 250 Go";

        String r = promptGen.pedagogique(promptuser);
        return r;

    }

    @GetMapping(value = "/raisonnement", produces = MediaType.TEXT_MARKDOWN_VALUE)
    public String raisonnement(){
        String r = promptGen.raisonnement();
        return r;
    }


    @GetMapping(value="/histoires/{typeHistoire}",produces=MediaType.TEXT_MARKDOWN_VALUE)
    public String histoire( @PathVariable String typeHistoire){
        String r=promptGen.genereHsitore(typeHistoire);
        return r;
    }

    @GetMapping(value = "/movies/{categorie}/{annee}", produces= MediaType.TEXT_MARKDOWN_VALUE)
    public String chat(@PathVariable String categorie, @PathVariable int annee) {
        String r =  promptGen.movies(categorie, annee);
        return r;
    }


}

