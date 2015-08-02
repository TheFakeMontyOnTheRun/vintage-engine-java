package br.odb.vintage;

import java.io.Serializable;

import br.odb.gameworld.Play;
import br.odb.libscene.World;

public class GameSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1848774213296625524L;
	Play gameplay;
	World scene;
	long timeStamp;
	
	public GameSession( Play gameplay, World scene ) {
		timeStamp = 0;
		this.scene = scene;
		this.gameplay = gameplay;
	}
}
