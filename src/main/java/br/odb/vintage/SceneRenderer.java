package br.odb.vintage;

import br.odb.libscene.CameraNode;
import br.odb.libscene.World;
import br.odb.utils.math.Vec3;

public interface SceneRenderer {
	void setScene( World scene );
	void spawnDefaultActor( Vec3 pos, float angleXZ );
	CameraNode getCurrentCameraNode();
}
