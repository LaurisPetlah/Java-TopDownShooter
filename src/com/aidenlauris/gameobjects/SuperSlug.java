package com.aidenlauris.gameobjects;

import java.awt.Color;

import com.aidenlauris.game.WorldMap;
import com.aidenlauris.gameobjects.util.Force;
import com.aidenlauris.gameobjects.util.HitBox;
import com.aidenlauris.gameobjects.util.HurtBox;
import com.aidenlauris.render.SoundHelper;

public class SuperSlug extends Slug {

	public SuperSlug(float x, float y) {
		super(x, y);
		
		this.health = 75;
		
		getCollisionBoxes().clear();
		addCollisionBox(new HitBox(this, 35, 35, true));
		addCollisionBox(new HurtBox(this, 40, 40, 20));
	}
	
	@Override
	public void kill() {
		
		super.kill();
		
		
		
		
		
		SoundHelper.makeSound("slug.wav");
		for (int i = 0; i < 4; i++){
		WorldMap.addGameObject(new Slug(this.x, this.y));
		}
	}

}
