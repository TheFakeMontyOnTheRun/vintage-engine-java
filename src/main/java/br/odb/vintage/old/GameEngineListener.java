package br.odb.vintage.old;

import br.odb.utils.FileServerDelegate;
import br.odb.vintage.actor.ActorSceneNode;

public interface GameEngineListener {
	
	public void onActorAdded( ActorSceneNode actor );

	public void needsToRefreshWindow(  boolean fastRefresh );

	public void loadMeshForActor( ActorSceneNode actor, String mesh, FileServerDelegate server );
	
	public void beforeTick();
	
	public void requestMapChange( String mapName );

	public void showHistory(int index);

	public void needsToRefreshLightning();

	public void needsToDisplayMessage(String information);

	public void showScreen(String screenClass);
}
