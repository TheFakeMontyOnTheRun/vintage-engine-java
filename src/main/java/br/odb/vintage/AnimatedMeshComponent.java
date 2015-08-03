package br.odb.vintage;

import java.util.HashMap;

import br.odb.vintage.actor.ActorState;
import br.odb.vintage.actor.Component;

public class AnimatedMeshComponent extends Component {

	public AnimatedMeshComponent(String name) {
		super(name);
	}

	public final HashMap< ActorState, MeshSequence > frames = new HashMap<>();
}
