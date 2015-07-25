package br.odb.vintage;

import br.odb.libstrip.GeneralTriangleMesh;

public class MeshSequence {
	private GeneralTriangleMesh[] sequence;
	private int currentFrame;
	
	void advanceFrame() {
		++currentFrame;
		
		while ( currentFrame < 0 ) {
			currentFrame = sequence.length - currentFrame;
		}
		
		while ( currentFrame >= sequence.length ) {
			currentFrame = currentFrame - sequence.length;
		}
	}
	
	GeneralTriangleMesh getCurrentFrame() {
		return sequence[ currentFrame ];
	}
}
