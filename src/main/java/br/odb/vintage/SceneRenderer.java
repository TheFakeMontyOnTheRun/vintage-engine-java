package br.odb.vintage;

import br.odb.libscene.CameraNode;
import br.odb.libscene.GroupSector;
import br.odb.libscene.World;
import br.odb.libstrip.TriangleMesh;
import br.odb.utils.math.Vec3;

public interface SceneRenderer {
	void setScene( World scene );
	void spawnDefaultActor( Vec3 pos, float angleXZ );
	CameraNode getCurrentCameraNode();
	void setDefaultMeshForActor(TriangleMesh enemy);
	void loadGeometryFromScene(GroupSector sector);
	void setAsReady();
	void flush();
}
