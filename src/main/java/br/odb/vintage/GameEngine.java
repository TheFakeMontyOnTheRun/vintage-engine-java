package br.odb.vintage;

import java.util.List;

import br.odb.gameworld.Play;
import br.odb.libscene.SceneNode;
import br.odb.libscene.Sector;
import br.odb.libscene.World;
import br.odb.utils.math.Vec3;
import br.odb.vintage.MultiplayerAgent.MultiplayerClient;
import br.odb.vintage.actor.ActorSceneNode;

/**
 * @author monty
 * 
 * 
 *         Concerns: - Takes a session and presents it properly, controlling
 *         time flow
 */
public class GameEngine implements Runnable, MultiplayerClient {

	GameSession currentGameSession;
	ScenePresenter presenter;
	MultiplayerAgent multiplayerAgent;

	// control elements - might be extracted into separate element
	long timeStep = 50;
	boolean running = true;
	boolean loaded = false;
	boolean updating;
	
	public GameEngine( String serverURL ) {
		if ( serverURL != null ) {
			multiplayerAgent = new MultiplayerAgent( 3, serverURL );
		}	
	}

	public void makeNewSessionFor(Play play, World scene,
			ScenePresenter presenter) {
		currentGameSession = new GameSession(scene, play);
		presenter.setScene(scene);
		this.presenter = presenter;
		loaded = true;
	}
	
	public void gameTick(ScenePresenter presenter) {
		
		if ( multiplayerAgent != null ) {	
			synchronized( multiplayerAgent.actors ) {
				
				presenter.renderer.clearActors();
				
				multiplayerAgent.localPlayer = presenter.renderer.getCurrentCameraNode();
				
				for ( ActorSceneNode node: multiplayerAgent.actors ) {
					presenter.renderer.addActor( node );			
				}				
			}
		}
	}

	// ------------------------------------------------------------------------------------------------------------
	// /**
	// *
	// */
	@Override
	public void run() {
		
		final List<SceneNode> srs = currentGameSession.scene
				.getAllRegionsAsList();
		
		Vec3 lastValidPosition = new Vec3();
		boolean inside;
		
		running = true;
		loaded = true;
		
		if ( multiplayerAgent != null ) {
			multiplayerAgent.startNetGame();
			new Thread( multiplayerAgent ).start();
		}
		
		while (running && loaded) {

			updating = true;
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gameTick( presenter );

			inside = false;
			
			for ( SceneNode sn : srs ) {
				
				if (sn instanceof Sector) {
					if (((Sector) sn).isInside(presenter.renderer
							.getCurrentCameraNode().localPosition)) {

						lastValidPosition.set(presenter.renderer
								.getCurrentCameraNode().localPosition);
						inside = true;
					}
				}
			}


			if (!inside) {
				presenter.renderer.getCurrentCameraNode().localPosition
						.set(lastValidPosition);
			}

			updating = false;
		}

		// ArrayList< ActorSceneNode> toBeAdded = new ArrayList<
		// ActorSceneNode>();
		// ActorSceneNode sons;
		// boolean actorHasChangedSector;
		// GroupSector originalSector;
		// ActorSceneNode actor;

		// actorHasChangedSector = true;

		// while ( ) {
		//
		//
		// try {
		//
		// Thread.sleep(timeStep);
		//
		// world.checkpointActors();
		//
		// if ( listener != null )
		// listener.beforeTick();
		//
		// if (delegate != null)
		// delegate.update(world);
		//
		// toBeAdded.clear();
		// int count = world.getTotalActors();
		//
		// for ( ActorSceneNode baseActor: world.getActorList() ) {
		//
		// actor = ( ActorSceneNode) baseActor;
		//
		// if (!actor.isAlive())
		// continue;
		//
		// actor.tick();
		//
		// originalSector = (GroupSector) world.getSector(actor.currentSector);
		//
		// if (actor.isPlayable()) {
		//
		// doGravity(actor);
		// }
		//
		//
		// findBestFitForActorFromSector(actor, originalSector);
		//
		// if (originalSector != world.getSector(actor.currentSector)) {
		//
		// actorHasChangedSector = true;
		//
		// if ( actor.getEmissiveLightningIntensity() > 0 )
		// needsToRefreshLightning = true;
		// }
		//
		// sons = actor.checkGenerated();
		//
		// if (sons != null) {
		// toBeAdded.add( ( ActorSceneNode)sons);
		// }
		// }
		//
		// int newId = 0;
		//
		// for ( ActorSceneNode spawn : toBeAdded ) {
		//
		// spawn.setId(count + newId++ );
		//
		// try {
		// spawn.getPosition().index = (-2);
		// } catch (Exception e) {
		//
		// }
		//
		// actorHasChangedSector = true;
		// world.addActor( spawn );
		// listener.onActorAdded( spawn );
		// }
		//
		// if (actorHasChangedSector) {
		// listener.needsToRefreshWindow( false );
		// }
		//
		// if ( needsToRefreshLightning ) {
		// listener.needsToRefreshLightning();
		// }
		//
		// actorHasChangedSector = false;
		//
		// } catch (Exception e) {
		//
		// }
		//
		//
		// }
		//
		// if ( world != null ) {
		//
		// for ( ActorSceneNode toBeKilled : world.getActorList() ) {
		//
		// if (!toBeKilled.isAlive())
		// continue;
		//
		// toBeKilled.setAlive( false );
		// }
		//
		// }
	}

	// //this would belong to World, wasn't for the dynamics of collision
	// between entities
	// private void findBestFitForActorFromSector( ActorSceneNode actor,
	// GroupSector originalSector) {
	//
	// int[] neighbours;
	// GroupSector sector = originalSector;
	//
	// if (!originalSector.contains(actor.getPosition())) {
	//
	// neighbours = originalSector.getNeighbours();
	//
	// for (int d = 0; d < neighbours.length; ++d) {
	//
	// sector = (GroupSector) world.getSector(neighbours[d]);
	//
	// if (sector.isMaster()) {
	// continue;
	// }
	//
	// if (neighbours[d] != 0 && sector.contains(actor.getPosition()))
	// break;
	//
	// sector = null;
	// }
	//
	// if (sector == null) {
	//
	// int size = world.getTotalSectors();
	// for (int d = 1; d < size; ++d) {
	//
	// sector = (GroupSector) world.getSector(d);
	//
	// if (sector.isMaster())
	// continue;
	//
	// if (sector.contains(actor.getPosition()))
	// break;
	//
	// sector = null;
	// }
	//
	// }
	//
	// if (sector == null || sector.isMaster()) {
	//
	// originalSector.onSectorLeftBy(actor);
	// actor.setCurrentSector(originalSector.getId());
	// actor.hit( originalSector );
	//
	// } else {
	//
	// originalSector.onSectorLeftBy(actor);
	// sector.onSectorEnteredBy(actor);
	//
	// if ( sector.getDoorCount() > 0 ) {
	// listener.needsToRefreshWindow( false );
	// }
	//
	// if ( delegate != null && sector != originalSector ) {
	// delegate.onSectorEntered(world, actor, sector);
	// }
	//
	// actor.setCurrentSector(sector.getId());
	// }
	// }
	// }

	public void stop() {
		this.running = false;
		loaded = false;
	}

	public void pause() {
		loaded = false;
	}

	public void resume() {
		loaded = true;
	}

	public void start() {		
		running = true;
	}

	@Override
	public void onDataReceived() {
		// TODO Auto-generated method stub
		
	}
}
