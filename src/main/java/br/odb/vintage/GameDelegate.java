/**
 * 
 */
package br.odb.vintage;

import br.odb.libscene.SceneNode;



/**
 * @author monty
 *
 */
public interface GameDelegate {
	void onMapChange( String oldMapName, String newMapName );
	void update();
	void onStart();
	void onSectorEntered( ActorSceneNode actor, SceneNode sector );
	boolean onActionAboutToBePerformed( ActorSceneNode actor,  ActorAction action );
	public boolean shouldOpenDoor( String doorId );
}
