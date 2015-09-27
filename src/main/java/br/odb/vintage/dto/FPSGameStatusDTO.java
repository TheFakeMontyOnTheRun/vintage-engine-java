package br.odb.vintage.dto;


public class FPSGameStatusDTO extends GameStatusDTO {

	public final PlayerStateDTO[] states;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4401257843813428147L;

	public FPSGameStatusDTO( PlayerStateDTO[] original )  {
		
		this.states = new PlayerStateDTO[ original.length ];
		
		for ( int c = 0; c < original.length; ++c ) {
			this.states[ c ] = original[ c ];
		}
	}
}
