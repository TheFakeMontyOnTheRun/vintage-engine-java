package br.odb.vintage;

import br.odb.libscene.CameraNode;
import br.odb.libscene.LightNode;
import br.odb.libstrip.GeneralTriangle;
import br.odb.libstrip.TriangleMesh;
import br.odb.utils.math.Vec3;

public interface SceneRenderer {
	void spawnDefaultActor( Vec3 pos, float angleXZ );
	CameraNode getCurrentCameraNode();
	void setDefaultMeshForActor(TriangleMesh enemy);
	void setAsReady();
	void flush();
	void addTriangleToStaticScene(GeneralTriangle gt);
	void addLight(LightNode ln);
}
