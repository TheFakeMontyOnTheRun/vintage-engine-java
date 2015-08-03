package br.odb.vintage.componentmodel;

import java.util.ArrayList;
import java.util.List;

public class ComponentContainer {

	private final List< Component > components = new ArrayList< Component >();
	private final GameEntity entity;
	
	public ComponentContainer( GameEntity entity ) {
		this.entity = entity;
	}
}
