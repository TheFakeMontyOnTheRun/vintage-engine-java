package br.odb.vintage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import br.odb.utils.math.Vec3;
import br.odb.vintage.actor.ActorSceneNode;

/**
 * Created by monty on 7/28/15.
 */
public class MultiplayerAgent implements Runnable {

	public static final String SERVER = "http://192.241.246.87:8080/MServerTest";
	
	volatile int gameId;
	volatile int playerId;
	ActorSceneNode playerNode;
	List< ActorSceneNode > actors = new ArrayList<>();

	void sendPosition(int id) throws IOException {

		Vec3 cameraPosition = playerNode.getAbsolutePosition();
		
		String query = String.format("id=%s&x=%s&y=%s&z=%s",
				URLEncoder.encode("" + id, "UTF8"),
				URLEncoder.encode("" + cameraPosition.x, "UTF8"),
				URLEncoder.encode("" + cameraPosition.y, "UTF8"),
				URLEncoder.encode("" + cameraPosition.z, "UTF8"));

		String received = blockSendHTTPGet(SERVER + "/Server?" + query);
		String[] positions = received.split(";");

		String[] coords;

		ActorSceneNode node;
		Vec3 v = new Vec3();
		
		synchronized (actors) {

			actors.clear();

			for (String pos : positions) {
				coords = pos.split("[ ]+");
				
				node = new ActorSceneNode( pos.toString() );
				

				v.x = Float.parseFloat(coords[0]);
				v.y = Float.parseFloat(coords[1]);
				v.z = Float.parseFloat(coords[2]);

				node.setPositionFromGlobal( v );				
				actors.add( node );
			}
		}
	}

	String blockSendHTTPGet(final String url) {
		String msg = "";
		try {
			URL urlObj = new URL(url);
			URLConnection connection;
			connection = urlObj.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;

			int responseCode = httpConnection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream in = httpConnection.getInputStream();

				InputStreamReader i = new InputStreamReader(in);
				BufferedReader str = new BufferedReader(i);
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = str.readLine()) != null) {
					sb.append(line);
				}

				msg = sb.toString();

			} else {
				System.out.println("Error code: " + responseCode);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	}

	@Override
	public void run() {

		String data = "" + blockSendHTTPGet(SERVER + "/GetId").trim().charAt(0);

		if (data == null || data.length() == 0) {
			return;
		}

		int id = Integer.parseInt(data);

		System.out.println("Player Id:" + id);

		while (true) {

			try {
				sendPosition(id);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void startNetGame() {
		String response = blockSendHTTPGet("http://localhost:8080/multiplayer-server/GetGameId?gameType=1");
		gameId = Integer.parseInt(response);
	}
}
