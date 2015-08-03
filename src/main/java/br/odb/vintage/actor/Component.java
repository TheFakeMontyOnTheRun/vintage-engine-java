package br.odb.vintage.actor;

public class Component {

	public final String name;
	private boolean enabled;
	
	public Component( String name ) {
		this.name = name;
	}
	
	public void setEnabled( boolean newEnabled ) {
		enabled = newEnabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
}
