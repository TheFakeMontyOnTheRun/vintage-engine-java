package br.odb.vintage.old;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import br.odb.libscene.CameraNode;
import br.odb.utils.Utils;
import br.odb.vintage.SceneRenderer;

public class ScenePresenter {

	SceneRenderer renderer;
	List< CameraNode > cameras = new ArrayList<>();

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

//    public void spawnActor(Vec3 v, float angleXZ) {
//        SceneActorNode actor = new SceneActorNode( "actor@" + v.toString() );
//        actor.localPosition.set( v );
//        actor.angleXZ = angleXZ;
//        renderer.actors.add( actor );
//    }

    public ScenePresenter(SceneRenderer renderer ) {
        this.renderer = renderer;
    }

    public void init(InputStream vertex, InputStream fragment) {

    	try {
            String vertexShader =  Utils.readFully(vertex, "utf8");
            String fragmentShader = Utils.readFully(fragment, "utf8");
            initDefaultMeshForActor();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void initDefaultMeshForActor() throws IOException {
//        GeneralTriangleMesh enemy;
//        WavefrontMaterialLoader matLoader = new WavefrontMaterialLoader();
//        List<Material> mats = matLoader.parseMaterials( context.getAssets().open( "gargoyle.mtl" ) );
//
//        WavefrontOBJLoader loader = new WavefrontOBJLoader( new GLES1TriangleFactory() );
//        ArrayList<GeneralTriangleMesh> mesh = (ArrayList<GeneralTriangleMesh>) loader.loadMeshes( context.getAssets().open("gargoyle.obj"), mats );
//
//        enemy = mesh.get( 0 );
//
//        for ( GeneralTriangle gt : enemy.faces ) {
//            renderer.sampleEnemy.faces.add(GLES1TriangleFactory.getInstance().makeTrigFrom(gt));
//        }
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
//
//    private void loadGeometryFromScene(GroupSector sector) {
//
//        for (GeneralTriangle isf : sector.mesh.faces) {
//            ++polyCount;
//            renderer.changeHue((GLES1Triangle) isf);
//            isf.flush();
//            renderer.addToVA((GLES1Triangle) isf);
//        }
//
//        for (SceneNode sr : sector.getSons()) {
//            if (sr instanceof GroupSector) {
//                loadGeometryFromScene((GroupSector) sr);
//            }
//        }
//    }
//
//    public void setScene(World scene) {
//        loadGeometryFromScene(scene.masterSector);
//        renderer.flush();
//    }
}

