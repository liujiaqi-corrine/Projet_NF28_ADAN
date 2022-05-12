package com.example.projet_nf28;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReadJSON {

    // Read the company.json file and convert it to a java object.
    public static Personne readUnePersonneJSONFile(Context context, int num) throws IOException,JSONException {

        // Read content of company.json
        String jsonText = readText(context, R.raw.usersdata);

        JSONArray jsonRootList = new JSONArray(jsonText);
        JSONObject jsonRoot = jsonRootList.getJSONObject(num);

        int id= jsonRoot.getInt("id");
        String name = jsonRoot.getString("name");

        JSONArray jsonArray = jsonRoot.getJSONArray("candidate");
        String[] candidate = new String[jsonArray.length()];

        for(int i=0;i < jsonArray.length();i++) {
            candidate[i] = jsonArray.getString(i);
        }

        JSONObject jsonAddress = jsonRoot.getJSONObject("adresse");
        String street = jsonAddress.getString("street");
        String city = jsonAddress.getString("city");
        Adresse adresse= new Adresse(street, city);

        Personne perso = new Personne();
        perso.setId(id);
        perso.setName(name);
        perso.setAdresse(adresse);
        perso.setCandidate(candidate);
        return perso;
    }

    public static Personne[] readPersonneJSONFile(Context context) throws IOException,JSONException {

        // Read content of company.json
        String jsonText = readText(context, R.raw.usersdata);

        JSONArray jsonRootList = new JSONArray(jsonText);

        Personne[] users = new Personne[jsonRootList.length()];

        for(int i=0;i < jsonRootList.length();i++) {
            JSONObject user = jsonRootList.getJSONObject(i);

            int id= user.getInt("id");
            String name = user.getString("name");

            JSONArray jsonArray = user.getJSONArray("candidate");
            String[] candidate = new String[jsonArray.length()];

            for(int j=0;j < jsonArray.length();j++) {
                candidate[j] = jsonArray.getString(j);
            }

            JSONObject jsonAddress = user.getJSONObject("adresse");
            String street = jsonAddress.getString("street");
            String city = jsonAddress.getString("city");
            Adresse adresse= new Adresse(street, city);

            Personne perso = new Personne();
            perso.setId(id);
            perso.setName(name);
            perso.setAdresse(adresse);
            perso.setCandidate(candidate);

            users[i] = perso;
        }

        return users;
    }

    private static String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

}
