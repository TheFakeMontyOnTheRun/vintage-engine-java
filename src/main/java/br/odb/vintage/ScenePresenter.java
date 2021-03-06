package br.odb.vintage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.odb.liboldfart.WavefrontMaterialLoader;
import br.odb.liboldfart.WavefrontOBJLoader;
import br.odb.libscene.CameraNode;
import br.odb.libscene.GroupSector;
import br.odb.libscene.LightNode;
import br.odb.libscene.MeshNode;
import br.odb.libscene.SceneNode;
import br.odb.libscene.World;
import br.odb.libstrip.GeneralTriangle;
import br.odb.libstrip.TriangleMesh;
import br.odb.libstrip.Material;
import br.odb.libstrip.builders.GeneralTriangleFactory;
import br.odb.utils.FileServerDelegate;
import br.odb.utils.Utils;
import br.odb.utils.math.Vec3;
import br.odb.vintage.actor.ActorSceneNode;

public class ScenePresenter {

	public final SceneRenderer renderer;
	public final List< CameraNode > cameras = new ArrayList<>();

//    final public Map<LightSource, GroupSector> lightsForPlace = new HashMap<LightSource, GroupSector>();
//    LightSource light0 = new LightSource(new Vec3(), 128);
//    final public ArrayList<LightSource> lightSources = new ArrayList<LightSource>();
//    public void lit(GroupSector s, LightSource ls) {
//
//        for (GeneralTriangle isf : s.mesh.faces) {
//            //         ( (GLES1Triangle ) isf ).light = ls.intensity;
//        }
//    }
//
//    public void findPlaceForLightSource(LightSource ls, World world) {
//        for (SceneNode sr : world.getAllRegionsAsList()) {
//            if (sr instanceof GroupSector) {
//                if (((GroupSector) sr).isInside(ls.position)) {
//                    lightsForPlace.put(ls, (GroupSector) sr);
//                    return;
//                }
//            }
//        }
//    }
//
//    public void processLights(World world) {
//        for (LightSource ls : lightSources) {
//            findPlaceForLightSource(ls, world);
//        }
//    }

    public void spawnActor(Vec3 v, float angleXZ) {
        ActorSceneNode actor = new ActorSceneNode( "actor@" + v.toString() );
        actor.localPosition.set( v );
        actor.angleXZ = angleXZ;
        renderer.addActor( actor );
    }

    public ScenePresenter(SceneRenderer renderer ) {
        this.renderer = renderer;
    }  
    

    public void init(InputStream vertex, InputStream fragment) {

    	try {
            String vertexShader =  Utils.readFully(vertex, "utf8");
            String fragmentShader = Utils.readFully(fragment, "utf8");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void initDefaultMeshForActor( FileServerDelegate fileServer ) throws IOException {
        TriangleMesh enemy;
        WavefrontMaterialLoader matLoader = new WavefrontMaterialLoader();
        List<Material> mats = matLoader.parseMaterials( fileServer.openAsset("gargoyle.mtl" ) );

        WavefrontOBJLoader loader = new WavefrontOBJLoader( new GeneralTriangleFactory() );
        ArrayList<TriangleMesh> mesh = (ArrayList<TriangleMesh>) loader.loadMeshes(  fileServer.openAsset("gargoyle.obj"), mats );

        enemy = mesh.get( 0 );

        renderer.setDefaultMeshForActor( enemy );
    }

    public void onLeft() {
        renderer.getCurrentCameraNode().angleXZ -= 10;
    }

    public void onRight() {
        renderer.getCurrentCameraNode().angleXZ += 10;
    }

    public void onWalkForward() {
        renderer.getCurrentCameraNode().localPosition.x += 10 * Math.sin(renderer.getCurrentCameraNode().angleXZ
                * (Math.PI / 180.0f));
        renderer.getCurrentCameraNode().localPosition.z -= 10 * Math.cos(renderer.getCurrentCameraNode().angleXZ
                * (Math.PI / 180.0f));
    }

	public void loadGeometryFromScene(GroupSector sector) {

		for (SceneNode sr : sector.getSons()) {
			if ( sr instanceof MeshNode ) {
				for (GeneralTriangle isf : ((MeshNode)sr).mesh.faces) {
					renderer.addTriangleToStaticScene(isf);
				}				
			} else if (sr instanceof GroupSector) {
				loadGeometryFromScene((GroupSector) sr);
			} else if (sr instanceof LightNode) {
				renderer.addLight((LightNode) sr);
			}
		}
	}

    public void setScene(World scene) {
    	loadGeometryFromScene( scene.masterSector );
    	renderer.flush();
    }
}

