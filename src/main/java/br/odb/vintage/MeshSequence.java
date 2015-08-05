package br.odb.vintage;

import br.odb.libstrip.TriangleMesh;

public class MeshSequence {
	private TriangleMesh[] sequence;
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
	
	TriangleMesh getCurrentFrame() {
		return sequence[ currentFrame ];
	}
}
