package br.odb.vintage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import br.odb.libscene.CameraNode;
import br.odb.utils.math.Vec3;
import br.odb.vintage.actor.ActorSceneNode;
import br.odb.vintage.dto.FPSGameStatusDTO;
import br.odb.vintage.dto.GameIdDTO;
import br.odb.vintage.dto.PlayerStateDTO;

/**
 * Created by monty on 7/28/15.
 */
public class MultiplayerAgent implements Runnable {
	public static final String HOST = "localhost";
	public static final String SERVER = "http://" + HOST + ":8080/multiplayer-server";
	final int gameType;
	volatile GameIdDTO gameId;
	public ActorSceneNode playerNode;
	public CameraNode localPlayer;
	List< ActorSceneNode > actors = new ArrayList<>();
	
	
	public interface MultiplayerClient {
		void onDataReceived();
	}
	
	MultiplayerAgent( int gameType ) {
		this.gameType = gameType;
	}
	
	synchronized void getGameStatus() {
		FPSGameStatusDTO state = (FPSGameStatusDTO) blockSendHTTPGet(SERVER + "/GetGameStatus?gameId=" + gameId.gameId);

		if ( state == null ) {
			return;
		}
		
		ActorSceneNode node;
		
		synchronized (actors) {

			actors.clear();
//			System.out.println( "------( " + gameId.playerId + " )" );

			for (PlayerStateDTO playerDTO: state.states ) {
				
				if ( playerDTO.id.equals( "" + gameId.playerId ) ) {
//					System.out.println( "skipping myself" );
					continue;
				}

				node = new ActorSceneNode( playerDTO.id );
				node.setPositionFromGlobal( playerDTO.position );
				node.angleXZ = playerDTO.angleXZ;
				node.id = playerDTO.id;
				actors.add( node );
				
//				System.out.println( "player id: " + node.id + " position: " + node.getAbsolutePosition() + " angle: " + node.angleXZ );
			}
		}
	}

	synchronized void sendPosition(int id) throws IOException {

		Vec3 cameraPosition = null;
		
		if ( localPlayer == null ) {
			return;
		}
		
		cameraPosition = localPlayer.localPosition;
		
		
		String query = String.format("gameId=%s&playerId=%s&x=%s&y=%s&z=%s&a=%s",
				URLEncoder.encode("" + gameId.gameId, "UTF8"),
				URLEncoder.encode("" + id, "UTF8"),
				URLEncoder.encode("" + cameraPosition.x, "UTF8"),
				URLEncoder.encode("" + cameraPosition.y, "UTF8"),
				URLEncoder.encode("" + cameraPosition.z, "UTF8"),
				URLEncoder.encode("" + localPlayer.angleXZ, "UTF8"));
		
		//System.out.println( "sending data about id = " + id + " -> " + query );

		blockSendHTTPGet(SERVER + "/SendMove?" + query);
	}

	Serializable blockSendHTTPGet(final String url) {
		try {
			URL urlObj = new URL(url);
			URLConnection connection;
			connection = urlObj.openConnection();
			HttpURLConnection httpConnection = (HttpURLConnection) connection;

			int responseCode = httpConnection.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream is = httpConnection.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				
				return (Serializable) ois.readObject();
			} else {
				System.out.println("Error code: " + responseCode);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void run() {

		int id = gameId.playerId;

		System.out.println("Player Id:" + id);

		while (true) {

			try {
				getGameStatus();
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
		gameId = (GameIdDTO) blockSendHTTPGet( SERVER + "/GetJavaGameId?gameType=" + gameType );
		
		System.out.println( "game id: " + gameId.gameId + "; player id: " + gameId.playerId );
	}
}
