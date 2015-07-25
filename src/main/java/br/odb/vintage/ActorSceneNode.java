package br.odb.vintage;

import java.util.HashMap;

import br.odb.libscene.DirectedSceneNode;
import br.odb.libscene.SceneNode;

public class ActorSceneNode extends DirectedSceneNode {

	private final HashMap< ActorState, MeshSequence > frames = new HashMap<>();
	
	@Override
	public void setFrom(DirectedSceneNode other) {
		super.setFrom(other);
	}

	@Override
	public void setFrom(SceneNode other) {
		super.setFrom(other);
	}


	public ActorSceneNode(SceneNode other) {
		super(other);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6739075743193322395L;
}
