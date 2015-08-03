package br.odb.vintage;

import java.io.Serializable;

import br.odb.gameworld.Play;
import br.odb.libscene.World;

public class GameSession implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1848774213296625524L;
	
	public final Play gameplay;
	public final World scene;
	public long timeStamp;
	
	public GameSession( World scene, Play gameplay ) {
		timeStamp = 0;
		this.scene = scene;
		this.gameplay = gameplay;
	}
	
	public void tick( long delta ) {
		timeStamp += delta;
	}
}
