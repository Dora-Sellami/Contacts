package com.example.appcontacts;
import org.json.JSONObject;
public class Contacts {
    private String ID, Nom, Adresse, Tel1, Tel2, Entreprise;

    public Contacts(JSONObject jObject) {

        this.Nom = jObject.optString("Nom");
        this.Adresse = jObject.optString("Adresse");
        this.Tel1 = jObject.optString("Tel1");
        this.Tel2 = jObject.optString("Tel2");
        this.Entreprise = jObject.optString("Entreprise");
    }



    public String getNom() {
        return Nom;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getTel1() {
        return Tel1;
    }

    public String getTel2() {
        return Tel2;
    }

    public String getEntreprise() {
        return Entreprise;
    }
}


