package br.odb.vintage.dto;

import java.io.Serializable;

public class GameIdDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8388938454126890556L;
	public final int gameId;
	public final int teamId;
	public final int playerId;
	
	/**
	 * 
	 */
	
	
	public GameIdDTO( int gameId, int teamId, int playerId ) {
		this.gameId = gameId;
		this.teamId = teamId;
		this.playerId = playerId;
	}

}
