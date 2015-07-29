package br.odb.vintage;

import br.odb.libscene.SceneNode;

/**
 * Created by monty on 6/28/15.
 */
public class SceneActorNode extends SceneNode {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2812950961405245971L;
	
	ActorState currentState;
	public float angleXZ;

    public SceneActorNode(String id) {
        super(id);
    }

    public SceneActorNode(SceneNode other) {
        super(other);
    }
}
