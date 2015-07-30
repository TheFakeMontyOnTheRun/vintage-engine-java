package br.odb.vintage.actor;

import br.odb.libscene.SceneNode;

/**
 * Created by monty on 6/28/15.
 */
public class ActorSceneNode extends SceneNode {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2812950961405245971L;
	
	ActorState currentState;
	public float angleXZ;

    public ActorSceneNode(String id) {
        super(id);
    }

    public ActorSceneNode(SceneNode other) {
        super(other);
    }
}
