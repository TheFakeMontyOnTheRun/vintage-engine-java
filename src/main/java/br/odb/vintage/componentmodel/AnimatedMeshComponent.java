package br.odb.vintage.componentmodel;

import java.util.HashMap;

import br.odb.vintage.MeshSequence;
import br.odb.vintage.actor.ActorState;

public class AnimatedMeshComponent extends Component {

	public AnimatedMeshComponent(String name) {
		super(name);
	}

	public final HashMap< ActorState, MeshSequence > frames = new HashMap<>();
}
