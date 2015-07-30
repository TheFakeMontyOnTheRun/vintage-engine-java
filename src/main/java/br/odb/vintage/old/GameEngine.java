package br.odb.vintage.old;

import br.odb.vintage.GameSession;
import br.odb.vintage.MultiplayerAgent;

/**
 * @author monty
 * 
 * 
 * Concerns:
 * - Takes a session and presents it properly, controlling time flow
 */
public class GameEngine implements Runnable {

	GameSession currentGameSession;
	ScenePresenter presenter;
	MultiplayerAgent multiplayer;
	
	//control elements - might be extracted into separate element
	long timeStep;
	boolean running = true;
	boolean loaded = false;
	boolean updating;

	//	/**
//	 * 
//	 */
//	/**
//	 * 
//	 */
//
//	/**
//	 * 
//	 */
//	public GameEngine(World world, GameEngineListener listener) {
//
//		this.world = world;
//		this.listener = listener;
//	}

//	// ------------------------------------------------------------------------------------------------------------
//	/**
//	 * 
//	 * @param actor
//	 * @param sectorId
//	 */
//	public void placeActor( ActorSceneNode actor, int sectorId) {
//
//		GroupSector sector = (GroupSector) world.masterSector.getChild(sectorId);
//		actor.moveTo(sector.getCenter());
//		actor.setCurrentSector(sectorId);
//		sector.onSectorEnteredBy(actor);
//	}
//
//	// ------------------------------------------------------------------------------------------------------------
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
//
//	}
//
//	// ------------------------------------------------------------------------------------------------------------
//	/**
//		 * 
//		 */
	@Override
	public void run() {
//
//		ArrayList< ActorSceneNode> toBeAdded = new ArrayList< ActorSceneNode>();
//		ActorSceneNode sons;
//		boolean actorHasChangedSector;
//		boolean needsToRefreshLightning = false;
//		GroupSector originalSector;
//		ActorSceneNode actor;
//
//		actorHasChangedSector = true;
//
//		while (running && loaded ) {
//			
//			updating = true;
//			needsToRefreshLightning = false;
//			
//			try {
//
//				Thread.sleep(timeStep);
//
//				world.checkpointActors();
//				
//				if ( listener != null )
//					listener.beforeTick();
//
//				if (delegate != null)
//					delegate.update(world);
//
//				toBeAdded.clear();
//				int count = world.getTotalActors();
//				
//				for ( ActorSceneNode baseActor: world.getActorList() ) {
//
//					actor = ( ActorSceneNode) baseActor;
//
//					if (!actor.isAlive())
//						continue;
//
//					actor.tick();
//
//					originalSector = (GroupSector) world.getSector(actor.currentSector);
//					
//					if (actor.isPlayable()) {
//						
//						doGravity(actor);
//					}
//
//
//					findBestFitForActorFromSector(actor, originalSector);
//
//					if (originalSector != world.getSector(actor.currentSector)) {
//
//						actorHasChangedSector = true;
//
//						if ( actor.getEmissiveLightningIntensity() > 0 )
//							needsToRefreshLightning = true;
//					}
//
//					sons = actor.checkGenerated();
//
//					if (sons != null) {
//						toBeAdded.add( ( ActorSceneNode)sons);
//					}
//				}
//				
//				int newId = 0;
//				
//				for ( ActorSceneNode spawn : toBeAdded ) {
//
//					spawn.setId(count + newId++ );
//
//					try {
//						spawn.getPosition().index = (-2);
//					} catch (Exception e) {
//
//					}
//
//					actorHasChangedSector = true;
//					world.addActor( spawn );
//					listener.onActorAdded( spawn );
//				}
//
//				if (actorHasChangedSector) {
//					listener.needsToRefreshWindow( false );
//				}
//				
//				if ( needsToRefreshLightning ) {					
//					listener.needsToRefreshLightning();
//				}
//				
//				actorHasChangedSector = false;
//
//			} catch (Exception e) {
//
//			}
//			
//			updating = false;
//		}
//		
//		if ( world != null ) {
//			
//			for ( ActorSceneNode toBeKilled : world.getActorList() ) {
//				
//				if (!toBeKilled.isAlive())
//					continue;
//				
//				toBeKilled.setAlive( false );
//			}
//			
//		}
	}

	
//	//this would belong to World, wasn't for the dynamics of collision between entities
//	private void findBestFitForActorFromSector( ActorSceneNode actor,
//			GroupSector originalSector) {
//
//		int[] neighbours;
//		GroupSector sector = originalSector;
//
//		if (!originalSector.contains(actor.getPosition())) {
//
//			neighbours = originalSector.getNeighbours();
//
//			for (int d = 0; d < neighbours.length; ++d) {
//
//				sector = (GroupSector) world.getSector(neighbours[d]);
//
//				if (sector.isMaster()) {
//					continue;
//				}
//
//				if (neighbours[d] != 0 && sector.contains(actor.getPosition()))
//					break;
//
//				sector = null;
//			}
//
//			if (sector == null) {
//
//				int size = world.getTotalSectors();
//				for (int d = 1; d < size; ++d) {
//
//					sector = (GroupSector) world.getSector(d);
//
//					if (sector.isMaster())
//						continue;
//
//					if (sector.contains(actor.getPosition()))
//						break;
//
//					sector = null;
//				}
//
//			}
//
//			if (sector == null || sector.isMaster()) {
//
//				originalSector.onSectorLeftBy(actor);
//				actor.setCurrentSector(originalSector.getId());
//				actor.hit( originalSector );
//
//			} else {
//
//				originalSector.onSectorLeftBy(actor);
//				sector.onSectorEnteredBy(actor);
//				
//				if ( sector.getDoorCount() > 0 ) {
//					listener.needsToRefreshWindow( false );
//				}
//				
//				if ( delegate != null && sector != originalSector ) {					
//					delegate.onSectorEntered(world, actor, sector);
//				}
//				
//				actor.setCurrentSector(sector.getId());
//			}
//		}
//	}

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

	public void setDelegate(GameDelegate delegate) {
//
//		this.delegate = delegate;
//		delegate.setGameEngine( this );
//		
//		for ( ActorSceneNode actor : world.getActorList() ) {
//			( ( ActorSceneNode ) actor ).setGameDelegate( delegate );
//		}
//		
//		for ( Sector sector : world ) {
//			GroupSector s = ( ( GroupSector) sector );
//			for ( Direction c : Direction.values() ) {
//
//				if ( s.getDoor( c ) != null ) {
//					s.getDoor( c ).setDelegate( delegate );
//				}
//			}
//		}
	}
//
//	public void requestMapChange(String mapName) {
//		
//		listener.requestMapChange(mapName);
//	}

	public void start() {
		running = true;
	}

//This would be a separate presenter?
//	public void showStory(int index ) {
//		
//		listener.showHistory( index );
//	}
//
	public void destroy() {
//		
//		while ( updating ) {
//			
//			try {
//				Thread.sleep( 10 );
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		world.destroy();
	}

//	public void restoreState( FileServerDelegate delegate ) throws FileNotFoundException, IOException {
//		world.loadSnapshotAt( delegate.openAsInputStream( "state" ) );		
//	}

//	public World getWorld() {
//
//		return world;
//	}
//
//	public GameEngineListener getListener() {
//
//		return listener;
//	}
//
//	public void showInformation(String information ) {
//		listener.needsToDisplayMessage( information );		
//	}
//
//	public void needRefreshVisibity() {
//		
//		listener.needsToRefreshWindow( false );
//	}
//
//	public void showScreen( String screenClass ) {
//		listener.showScreen( screenClass );		
//	}
}
