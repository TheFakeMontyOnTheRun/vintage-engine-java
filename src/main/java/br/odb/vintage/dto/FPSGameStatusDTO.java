package br.odb.vintage.dto;


public class FPSGameStatusDTO extends GameStatusDTO {

	public final PlayerStateDTO[] states;
	public final BeamStateDTO[] beams;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4401257843813428147L;

	public FPSGameStatusDTO( PlayerStateDTO[] original, BeamStateDTO[] lasers )  {
		
		this.states = new PlayerStateDTO[ original.length ];
		this.beams = new BeamStateDTO[ lasers.length ];
		
		for ( int c = 0; c < original.length; ++c ) {
			this.states[ c ] = original[ c ];
		}
		
		for ( int c = 0; c < lasers.length; ++c ) {
			this.beams[ c ] = lasers[ c ];
		}
	}
}
