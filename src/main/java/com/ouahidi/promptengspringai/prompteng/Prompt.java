package com.ouahidi.promptengspringai.prompteng;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
@Service("Prompt")
public class Prompt {
    private ChatClient chatClient;
    public Prompt(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    String promptpedagogique =
            """
            Vous êtes spécialiste de marketing, je voudrai mettre en vente un 
            PC portable et tu vas me rédiger 
            une annonce de vente sur le site https://www.avito.ma/
           utilise des puces pour la clarté du texte.   
        """;
    String promptraisonnement=
            """
            Tu es spécialiste d'évaluation de la qualité de filières d'enseignements universitaire.
            Le but est que tu propose un plan d'action pour rémédier au problème rencontré par l'équipe pédgogique.
            
            Tu vas recommander un plan d'action détaillé défini  étape par étape que l’université devra suivre 
            Présente ta  réponse sous le format suivant :
               {
                "question": <la question d'entrée à laquelle il faut répondre>,
                "reflection": <réfléchissez à des options et au plan d'action pour chaque option>,
                 "action": <l'action à entreprendre parmi les trois options présentées">,
                 "raison": <le raisonnement derrière ta réponse>
                 "étapes" : <plan étape par étape mettant en oeuvre l'action>           
                   utilises les puces ou la numérotation pour mieux clarifier le texte
                }     
            """;

    String promptMoviesSystem =
             """
             Tu es un cinéphile. Tu es capable de donner  le meilleur film de l'année  {annee}  et d'une catégorie {categorie} 
              de films donnés  sous forme:
                   #Titre:  le titre du film 
                   # Les acteurs:  les acteurs principaux du film
                   # Le producteur : le producteur du film
                   # Résumé: un résumé succinct
            """;

    String promptHistoireSystem ="""
                Tu es un conteur d'histoires.
                """;

    public String pedagogique(String promptuser)
    {
        String r = chatClient.prompt()
                .system(promptpedagogique)
                .user(promptuser) //  les caractéristiques: marque PC, RAM, Disque, processeur, etc
                .call().content();
        return r;
    }


    public String raisonnement(){
        String s1= "Les enseignants de notre université ont constaté: ";
        String s2= "Un taux de réussite faible ne dépasse pas 20% en première année ";
        String s3=" Une baisse de niveau en mathématiques, et dans la langue française et un manque d'assuidité";
        String s= s1+s2+s3;
        String r = chatClient.prompt()
                .system(promptraisonnement)
                .user(s)
                .call().content();
        return r;
        }


    public String genereHsitore( String typeHistoire) {
        String r = chatClient
                .prompt()
                .system(promptHistoireSystem)
                .user(typeHistoire)
                .call().content();
        return r;
    }


    public String movies(String categorie, int annee){
        String r = chatClient.prompt()
                .system(promptMoviesSystem)
                .user("annee = "+annee)
                .user("categorie= "+categorie)
                .call().content();
        return r;

    }

}


