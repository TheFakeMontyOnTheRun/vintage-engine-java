package br.odb.vintage.dto;

import java.io.Serializable;

import br.odb.utils.math.Vec3;

public class BeamStateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -636684275336776920L;

	public final Vec3 p0 = new Vec3();
	public final Vec3 p1 = new Vec3();
	long decayInMS;
	
	public BeamStateDTO( Vec3 p0, Vec3 p1, long decayInMS ) {
		this.p0.set( p0 );
		this.p1.set( p1 );
		this.decayInMS = decayInMS;
	}	
}
