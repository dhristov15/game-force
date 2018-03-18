package entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	
	private Vector3f position;
	private Vector3f color;
	private Vector3f attenuation = new Vector3f(1,0,0);
	
	public Light(Vector3f positon, Vector3f color) {
		this.position = positon;
		this.color = color;
	}
	public Light(Vector3f positon, Vector3f color, Vector3f attenuation) {
		this.position = positon;
		this.color = color;
		this.attenuation = attenuation;
	}
	
	public Vector3f getAttenuation() {
		return attenuation;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getColour() {
		return color;
	}
	public void setColour(Vector3f color) {
		this.color = color;
	}
	
}
