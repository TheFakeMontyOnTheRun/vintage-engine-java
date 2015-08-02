package br.odb.vintage;

import java.util.ArrayList;
import java.util.List;

public class PhysicsEngine {
	final public List<RigidBodyComponent> rigidBodies = new ArrayList<>();
	
	// ------------------------------------------------------------------------------------------------------------
//	private void doGravity( ActorSceneNode actor) {
//		GroupSector sector = (GroupSector) world.getSector(actor
//				.getCurrentSector());
//		GroupSector candidate = sector;
//		GroupSector groundSector = sector;
//
//		while (groundSector != null
//					&& (groundSector.cachedNeighBours[ Direction.FLOOR ] != null )) {
//
//				candidate = groundSector.cachedNeighBours[ Direction.FLOOR ];
//
//				if (candidate.isMaster()) {
//					continue;
//				}
//
//				groundSector = candidate;
//
//			}
//
//			while ((groundSector.getY0() + actor.getDY()) > (actor
//					.getPosition().y)) {
//				actor.consume(ActorConstants.MOVE_UP);
//			}
//
//			while ((groundSector.getY0() + actor.getDY()) < (actor
//					.getPosition().y)) {
//
//				actor.consume(ActorConstants.MOVE_DOWN);
//			}
//	}	
}
