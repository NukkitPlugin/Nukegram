package kr.mohi.nukegram;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.json.JSONTokener;

import cn.nukkit.plugin.PluginBase;
import kr.mohi.nukegram.type.Response;

public class Nukegram extends PluginBase {
	public static final String REQUEST_URL = "https://api.telegram.org/bot%s/%s";
	private static String token = null;
	
	@Override
	public void onEnable() {
		
	}
	
	public static String getToken() {
		return token;
	}
	
	public static Response request(final String method, final JSONObject parameters){
        try{
            final URL url = new URL(String.format(Nukegram.REQUEST_URL, Nukegram.getToken(), method));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            if(parameters.length() > 0){
                connection.setDoOutput(true);
                try{
                	final OutputStream stream = connection.getOutputStream();
                    stream.write(parameters.toString().getBytes(StandardCharsets.UTF_8));
                }catch(Exception e){
                	e.printStackTrace();
                	return null;
                }
                //this.getLogger().finest(String.format("%s%n", parameters.toString(2)));
            }

            final JSONObject response = new JSONObject(new JSONTokener(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)));
            //this.getLogger().finer(String.format("%s%n", response.toString(2)));

            return Response.create(response);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
