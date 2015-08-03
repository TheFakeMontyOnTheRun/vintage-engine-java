/**
 * 
 */
package br.odb.vintage.old;

import br.odb.libscene.SpaceRegion;
import br.odb.vintage.actor.ActorAction;
import br.odb.vintage.actor.ActorSceneNode;

/**
 * @author monty
 *
 */
//should this become EntityActuator?
public interface GameDelegate {
	void onMapChange( String oldMapName, String newMapName );
	void update();
	void onStart();
	void onSectorEntered( ActorSceneNode actor, SpaceRegion region );
	boolean onActionAboutToBePerformed( ActorSceneNode actor,  ActorAction action );
	public boolean shouldOpenDoor( String doorId );
}
