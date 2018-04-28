package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float zoomDistance = 0;
	private float angleAroundPlayer = 1;
	
	//private static final float RUN_SPEED = 0.25f;
	//private static final float TURN_SPEED = 0.25f;

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch = 0;
	private float yaw = 0;
	private float roll;
	
	private Player player;

	public Camera(Player player) {
		this.player = player;
	}

	public void move() {
		calculateZoom();
		//calculatePitch();
		//calculateAngleAroundPlayer();
		float horizontalDistance = calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		calculateCameraPosition(horizontalDistance,verticalDistance);
		this.yaw = 180 - (player.getRotY() + angleAroundPlayer);
//		if(!Mouse.isButtonDown(2)) {
//			pitch = 20;
//		}
//		if(!Mouse.isButtonDown(2)) {
//			angleAroundPlayer = 0;
		//}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	private void calculateCameraPosition(float horizontalDistance, float verticalDistance) {
		float theta = player.getRotY() + angleAroundPlayer;
		float offsetX = (float) (horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizontalDistance * Math.cos(Math.toRadians(theta)));
		position.x = player.getPosition().x - offsetX;
		position.z = player.getPosition().z - offsetZ;
		position.y = player.getPosition().y + verticalDistance + 7;
	}
	
	
	private float calculateHorizontalDistance() {
		float horizontalDistance = (float) (zoomDistance * Math.cos(Math.toRadians(pitch)));
		if(horizontalDistance < 0) {
			horizontalDistance = 0;
			zoomDistance = 0;
		}
		return horizontalDistance;
	}
	
	private float calculateVerticalDistance() {
		float verticalDistance = (float) (zoomDistance * Math.sin(Math.toRadians(pitch)));
		if(verticalDistance < 0) {
			verticalDistance = 0;
			zoomDistance = 0;
		}
		return verticalDistance;
	}
	
	private void calculateZoom() {
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		zoomDistance -= zoomLevel;
		if(zoomDistance > 300) {
			zoomDistance = 300;
		}
	}
//	private void calculatePitch() {
//		if(Mouse.isButtonDown(0)) {
//			float pitchChange = Mouse.getDY() * 0.1f;
//			pitch -= pitchChange;
//			float angleChange = Mouse.getDX() * 0.3f;
//			angleAroundPlayer -= angleChange;
//		}
//	}
//	private void calculateAngleAroundPlayer() {
//		if(Mouse.isButtonDown(1)) {
//			float angleChange = Mouse.getDX() * 0.3f;
//			angleAroundPlayer -= angleChange;
//		}
//	}

}
