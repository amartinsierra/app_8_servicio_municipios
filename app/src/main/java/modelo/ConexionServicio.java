package modelo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ConexionServicio {
    private String urlBase="https://apiv1.geoapi.es/municipios?type=JSON&key=&sandbox=1&CPRO=";

    public List<String> obtenerMunicipios(int codigoProvincia){
        List<String> municipios=new ArrayList<>();
        String cad = "", aux;
        //conectamos con el servicio de municipios
        BufferedReader bf=null;
        try {
            URL url = new URL(urlBase+codigoProvincia);
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;

            http.setDoInput(true);
            http.setRequestProperty("Accept", "application/json");

            //obtener Stream de entrada para leer los datos
            //enviados desde la URL
            InputStream is = http.getInputStream();
            bf = new BufferedReader(new InputStreamReader(is));
            //vamos leyendo las líneas enviadas por el servicio y las unimos
            //en la variable cad
            while ((aux = bf.readLine()) != null) {
                cad += aux + "\n";
            }
            JSONObject json=new JSONObject(cad);
            JSONArray array=(JSONArray)json.get("data");
            for(int i=0;i<array.length();i++){
                JSONObject ob=(JSONObject)array.get(i);
                municipios.add((String)ob.get("DMUN50"));
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return municipios;
    }
}
