package master.ccm.entity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpResponse;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import master.ccm.ccm2yugiohproject.Game_activity;
import master.ccm.entity.subcard.CardInGame;
import master.ccm.entity.subcard.Monstre;

public class HttpRequest {
    private String urlAdress = "https://ia-yugioh.herokuapp.com/";
    private static CardJson[] tabmonstresForJson;
    //private CardJson[] listCardDef = new CardJson[5];

    public void sendPost(IABot iaBot, CardInGame[] listcard, Monstre monstreAttaquant) {
        CardJson[] tabMonstre = new CardJson[10];
        CardJson monstreAtk = new CardJson();
        monstreAtk.setId(monstreAttaquant.getIdNumberInGame());
        monstreAtk.setName(monstreAttaquant.getName());
        monstreAtk.setAtk(monstreAttaquant.getAtk());
        monstreAtk.setDef(monstreAttaquant.getDef());
        if(monstreAttaquant.getPosition().equals("ATK")){

            monstreAtk.setPosition("attaque");
        }else{
            monstreAtk.setPosition("defense");
        }
        tabMonstre[0] = monstreAtk;
        //Log.i("JSON","tabmonstreAtk"+tabMonstre[1]);
        //Log.i("JSON","tabmonstreAtk "+tabMonstre[0].getName());

        for (int i = 5; i < 10; i++) {
            CardJson monstreDef = new CardJson();
            if (listcard[i - 5] != null) {

                monstreDef.setId(listcard[i - 5].getIdNumberInGame());
                monstreDef.setName(listcard[i - 5].getName());
                monstreDef.setAtk(((Monstre) listcard[i - 5]).getAtk());
                monstreDef.setDef(((Monstre) listcard[i - 5]).getDef());
                //monstreDef.setPosition(((Monstre) listcard[i - 5]).getPosition());
                if(((Monstre) listcard[i - 5]).getPosition().equals("ATK")){

                    monstreDef.setPosition("attaque");
                }else{
                    monstreDef.setPosition("defense");
                }
                tabMonstre[i] = monstreDef;
                //Log.i("JSON","monstreDef "+tabMonstre[i].getName());
            } else {
                tabMonstre[i] = null;
            }
        }
        tabmonstresForJson = tabMonstre;
        Log.d("JSON", "SendJSON");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(urlAdress);
                    String response = "";

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    /*JSONObject jsonParam = new JSONObject();
                    JSONArray array = new JSONArray();*/
                    //JsonParser jsonParser = new JsonParser();

                    ArrayList<CardJson> objList = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        if(tabmonstresForJson[i] != null) {
                            objList.add(tabmonstresForJson[i]);
                        }else{
                            CardJson newCard = new CardJson();
                            objList.add(newCard);
                        }
                        //Gson gsonBuilder = new GsonBuilder().create();
                        //String jsonFromPojo = gsonBuilder.toJson(tabmonstresForJson[i]);
                        //array.put(jsonFromPojo);

                    }

                    //Type listType = new TypeToken<List<CardJson>>() {}.getType();

                    IaDto iaDto = new IaDto();
                    iaDto.setTabmonstres(objList);
                    Gson gson = new Gson();
                    String json = gson.toJson(iaDto);



                     //String json = new Gson().toJson(objList);
                    //array = jsonParser.parse(tabmonstresForJson).getAsJsonArray();
                    //jsonParam.put("monstreAttaquant", monstreAtk);
                    //jsonParam.put("tabmonstres", json);
                    //String jsonString = jsonParam.toString();
                    //Log.i("jsonString", jsonString);
                    /*String jsonstring = String.valueOf("{\"tabmonstres\":"+ tabMonstre.+"}");
                    Log.i("JSON string", jsonstring);
                    byte[] out = jsonstring.getBytes(StandardCharsets.UTF_8);
                    int length = out.length;

                    conn.setFixedLengthStreamingMode(length);
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    conn.connect();
                    try(OutputStream os = conn.getOutputStream()) {
                        os.write(out);
                    }*/

                    /*CardJson[] array = (CardJson[]) jsonParam.get("tabmonstres");
                    Log.i("JSON", "ici send : "+ array[0].getName());*/
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeChars(jsonParam);
                    os.writeBytes(json);
                    //os.writeBytes(tabMonstre.toString());
                    //Log.i("JSON", "Debut Tableau");

                    //Log.i("JSON", String.valueOf(tabMonstre.length));
                    //Log.i("JSON", tabMonstre.toString());
                    //os.writeBytes(String.valueOf(tabMonstre));
                    //os.write(String.valueOf(tabMonstre));
                    //HttpResponse response=conn.execute(post);
                    os.flush();
                    os.close();
                    int responseCode=conn.getResponseCode();

                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        String line;
                        BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        while ((line=br.readLine()) != null) {
                            response += line;
                        }
                    }
                    else {
                        response="";

                    }
                    Log.i("JSON", "res : "+response);
                    Log.i("JSON", "STATUS : "+String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());
                    //Game_activity.
                    //recuperation de la reponse
                    JSONObject obj = new JSONObject(response);
                    String action = obj.getString("action");


                    Log.i("JSON Action ", ""+action);

                    if(action.equals("RIEN"))
                    {
                        Log.i("JSON Action Rien","Rien");
                    }else if(action.equals("ATTAQUER")){
                        Log.i("JSON Action Attaquer", "Attaquer");
                        JSONObject jsonmonstreDef = obj.getJSONObject("monstreAttaque");
                        String iddef = jsonmonstreDef.getString("id");
                        Log.i("JSON idDef ", ""+iddef);

                        JSONObject jsonmonstreAtk = obj.getJSONObject("monstreAttaquant");
                        String idatk = jsonmonstreAtk.getString("id");
                        Log.i("JSON idAtk ", ""+idatk);
                        iaBot.AttaqueIA(idatk,iddef);
                    }
                    //System.out.println(action);
                    //result = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");


                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
    }
}
