package EngineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import guis.GuiRenderer;
import guis.GuiTexture;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		// TERRAIN TEXTURE

		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy3"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("mossPath256"));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

		// TERRAIN TEXTURE

		//RawModel model = OBJLoader.loadObjModel("tree", loader);

		//TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("tree")));
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
				new ModelTexture(loader.loadTexture("grassTexture")));
		grass.getTexture().setHasTransparency(true);
		grass.getTexture().setUseFakeLighting(true);
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture("fern"));
		fernTextureAtlas.setNumberOfRows(2);
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),
				fernTextureAtlas);
		fern.getTexture().setHasTransparency(true);
		TexturedModel flower = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
				new ModelTexture(loader.loadTexture("flower")));
		flower.getTexture().setHasTransparency(true);
		flower.getTexture().setUseFakeLighting(true);
		ModelTexture lowPolyTreeTextureAtlas = new ModelTexture(loader.loadTexture("lowPolyTree"));
		lowPolyTreeTextureAtlas.setNumberOfRows(2);
		//TexturedModel bobble = new TexturedModel(OBJLoader.loadObjModel("lowPolyTree", loader),
				//lowPolyTreeTextureAtlas);
		TexturedModel stall = new TexturedModel(OBJLoader.loadObjModel("stall", loader),
				new ModelTexture(loader.loadTexture("stallTexture")));
		stall.getTexture().setHasTransparency(false);
		stall.getTexture().setUseFakeLighting(true);

		TexturedModel pineTree = new TexturedModel(OBJLoader.loadObjModel("pine", loader),
				new ModelTexture(loader.loadTexture("pine")));
		
		Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightMap");
		//Terrain terrain2 = new Terrain(-1, -1, loader, texturePack, blendMap, "heightMap");

		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random(676452);
		for (int i = 0; i < 400; i++) {
			if (i % 5 == 0) {
//				float x = random.nextFloat() * 800 - 400;
//				float z = random.nextFloat() * -600;
//				float y = terrain.getHeightOfTerrain(x, z);
//				entities.add(new Entity(grass, new Vector3f(x, y, z), 0, random.nextFloat() * 360, 0, 1.8f));
//				x = random.nextFloat() * 800 - 400;
//				z = random.nextFloat() * -600;
//				y = terrain.getHeightOfTerrain(x, z);
//				entities.add(new Entity(flower, new Vector3f(x, y, z), 0, random.nextFloat() * 360, 0, 2.3f));
//				// entities.add(new Entity(stall,
//				// new Vector3f(random.nextFloat() * 400 - 200, 0, random.nextFloat() * -400),
//				// 0, 0, 0, 2.3f));
				float x = random.nextFloat() * 400;
				float z = random.nextFloat() * -400;
				float y = terrain.getHeightOfTerrain(x, z);
				entities.add(new Entity(fern, random.nextInt(4), new Vector3f(x, y, z), 0, random.nextFloat() * 360, 0, 0.9f));
			}
			if (i % 3 == 0) {
				float x = random.nextFloat() * 800;
				float z = random.nextFloat() * -800;
				float y = terrain.getHeightOfTerrain(x, z);
				entities.add(
						new Entity(pineTree,random.nextInt(4), new Vector3f(x,y,z),
								0, random.nextFloat() * 360, 0, 1.5f));//random.nextFloat() * 0.1f + 0.6f
//				x = random.nextFloat() * 800;
//				z = random.nextFloat() * -800;
//				y = terrain.getHeightOfTerrain(x, z);
//				entities.add(new Entity(staticModel,
//						new Vector3f(x,y,z), 0, 0, 0,
//						random.nextFloat() * 1 + 4));
			}

		}

		List<Light> lights = new ArrayList<>();
		lights.add(new Light(new Vector3f(0,1000,-7000), new Vector3f(0.4f, 0.4f, 0.4f)));
		lights.add(new Light(new Vector3f(185,10,-293), new Vector3f(2,2,0), new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(370,17,-300), new Vector3f(2,2,0), new Vector3f(1,0.01f,0.002f)));
		lights.add(new Light(new Vector3f(293,7,-305), new Vector3f(2,2,0), new Vector3f(1,0.01f,0.002f)));

		
		TexturedModel lamp = new TexturedModel(OBJLoader.loadObjModel("lamp", loader),
				new ModelTexture(loader.loadTexture("lamp")));
		lamp.getTexture().setUseFakeLighting(true);
		entities.add(new Entity(lamp, new Vector3f(185, -4.7f, -293), 0, 0, 0, 1));
		entities.add(new Entity(lamp, new Vector3f(370, 4.2f, -300), 0, 0, 0, 1));
		entities.add(new Entity(lamp, new Vector3f(293, -6.8f, -305), 0, 0, 0, 1));

		MasterRenderer renderer = new MasterRenderer(loader);

		RawModel bunnyModel = OBJLoader.loadObjModel("person", loader);
		TexturedModel stanfordBunny = new TexturedModel(bunnyModel,
				new ModelTexture(loader.loadTexture("playerTexture")));

		Player player = new Player(stanfordBunny, new Vector3f(200, 0, -500), 0, 0, 0, 0.75f);
		Camera camera = new Camera(player);
		
		List<GuiTexture> guis = new ArrayList<>();
		GuiTexture gui = new GuiTexture(loader.loadTexture("health"), new Vector2f(-0.70f,-0.75f), new Vector2f(0.25f,0.25f));
		guis.add(gui);
		
		GuiRenderer guiRenderer = new GuiRenderer(loader);

		while (!Display.isCloseRequested()) {
			camera.move();
			player.move(terrain);
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			for (Entity entity : entities) {
				renderer.processEntity(entity);
			}
			renderer.render(lights, camera);
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();
		}

		guiRenderer.cleanUp();
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}