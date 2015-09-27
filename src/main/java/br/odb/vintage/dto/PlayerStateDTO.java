package br.odb.vintage.dto;

import java.io.Serializable;

import br.odb.utils.math.Vec3;

public class PlayerStateDTO implements Serializable {

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 3306253737599638600L;
	/**
	 * 
	 */
	
	public final Vec3 position = new Vec3();
	public final float angleXZ;
	public final float angleYZ;
	public final String id;
	
	public PlayerStateDTO( String id, Vec3 position, float angleXZ, float angleYZ ) {
		this.id = id;
		this.position.set( position );
		this.angleXZ = angleXZ;
		this.angleYZ = angleYZ;
	}
}
