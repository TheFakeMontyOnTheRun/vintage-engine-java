package br.odb.vintage.actor;

import java.util.ArrayList;
import java.util.List;

import br.odb.vintage.componentmodel.GameEntity;

public class ComponentContainer {

	private final List< Component > components = new ArrayList< Component >();
	private final GameEntity entity;
	
	public ComponentContainer( GameEntity entity ) {
		this.entity = entity;
	}
}
