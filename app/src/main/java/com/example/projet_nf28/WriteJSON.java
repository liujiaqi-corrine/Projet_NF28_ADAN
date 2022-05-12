package com.example.projet_nf28;

import android.util.JsonWriter;

import java.io.IOException;
import java.io.Writer;

public class WriteJSON {
    public static void writeJsonStream(Writer output, Personne perso ) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);

        jsonWriter.beginObject();// begin root

        jsonWriter.name("id").value(perso.getId());
        jsonWriter.name("name").value(perso.getName());

        String[] candidate = perso.getCandidate();

        // "websites": [ ....]
        jsonWriter.name("websites").beginArray(); // begin websites
        for(String c : candidate) {
            jsonWriter.value(c);
        }
        jsonWriter.endArray();// end websites

        // "address": { ... }
        jsonWriter.name("adresse").beginObject(); // begin address
        jsonWriter.name("street").value(perso.getAdresse().getStreet());
        jsonWriter.name("city").value(perso.getAdresse().getCity());
        jsonWriter.endObject();// end address

        // end root
        jsonWriter.endObject();
    }


    public static Personne createArtiste() {

        Personne perso = new Personne();
        perso.setId(123);
        perso.setName("Apple");

        String[] candidate = { "Offre1"};
        perso.setCandidate(candidate);

        Adresse address = new Adresse();
        address.setCity("Cupertino");
        address.setStreet("1 Infinite Loop");

        perso.setAdresse(address);

        return perso;
    }



}
