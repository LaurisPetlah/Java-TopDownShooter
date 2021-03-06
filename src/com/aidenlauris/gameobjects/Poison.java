/*Aiden Gimpel, Lauris Petlah
 * January 20th, 2019
 * Poison
 * PoisonWalker creates this when it walks to damage player
 */

package com.aidenlauris.gameobjects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import com.aidenlauris.game.util.Time;
import com.aidenlauris.gameobjects.util.CollisionBox;
import com.aidenlauris.gameobjects.util.Entity;
import com.aidenlauris.gameobjects.util.HitBox;
import com.aidenlauris.gameobjects.util.HurtBox;
import com.aidenlauris.gameobjects.util.Team;
import com.aidenlauris.render.PaintHelper;

public class Poison extends Entity {
	
	//timers for entity actions
	long lifespan = Time.alert(145);
	long damageTimer = Time.alert(10);

	
	/**
	 * Initiates this entity at a coordinate
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Poison(float x, float y) {
		super(x, y);
		addCollisionBox(new HurtBox(this, 30, 30, 1));
		team = Team.ENEMY;
	}


	@Override
	public void collisionOccured(CollisionBox theirBox, CollisionBox myBox) {
		
		
		//since this is a static entity, we dont want it to damage the player every tick, we make it so that we only damage the player every second instead
		if(theirBox.getOwner() instanceof Player && Time.alertPassed(damageTimer)) {
			((Entity)theirBox.getOwner()).damage(10);
			damageTimer = Time.alert(60);
			
		}

	}

	@Override
	public void update() {
		//kill after alert passed
		if (Time.alertPassed(lifespan)) {
			kill();
		}
	}
@Override
public Graphics2D draw(Graphics2D g2d) {
	
	//draw a green rectangle
	float drawX, drawY;
	drawX = PaintHelper.x(x);
	drawY = PaintHelper.y(y);
	
	Shape s = new Rectangle2D.Float(drawX - 15, drawY - 15, 30, 30);

	g2d.setColor(Color.GREEN);
	g2d.fill(s);
	
	return g2d;
}
}
