import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class WorldMap {
	Tile[] tiles = new Tile[36 * 36];
	private Camera camera;
	static ArrayList<Entity> entities = new ArrayList<>();

	static ArrayList<CollisionBox> collisionBoxes = new ArrayList<>();

	public WorldMap() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile(i % 36 * Tile.size, Tile.size * (int) (i / 36),
					new Color(150 + 25 * (i % 5), 150 - 25 * (i % 5), 150));
		}

		// addEntity(new Wall(1, 1, 1, 1));
		System.out.println(entities.size());
	}

	public void update() {
		for (Entity e : entities) {
			e.update();
		}
		pruneEntities();
		for (CollisionBox cb : collisionBoxes) {
			for (CollisionBox hb : collisionBoxes) {
				if (cb == hb)
					continue;
				CollisionHelper.sendReply(cb, hb);

			}
		}
	}

	public void addEntity(Entity e) {

		e.setWorldMap(this);
		entities.add(e);

		collisionBoxes.add(e.getCollisionBox());
	}

	public void pruneEntities() {
		Iterator<Entity> i = entities.iterator();
		while (i.hasNext()) {
			Entity e = i.next();
			if (e.health <= 0) {
				Iterator<CollisionBox> j = collisionBoxes.iterator();
				while (j.hasNext()) {
					CollisionBox cb = j.next();
					if (cb.getOwner() == e) {
						j.remove();
					}
				}
				i.remove();
			}
		}
	}

	public Player getPlayer() {

		for (Entity e : entities) {
			if (e instanceof Player) {
				return (Player) e;
			}
		}
		return null;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;

	}

	Camera getCamera() {
		return camera;
	}

}
