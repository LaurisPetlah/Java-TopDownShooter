package com.aidenlauris.items;

import com.aidenlauris.gameobjects.Bullet;
import com.aidenlauris.gameobjects.MachineBullet;
import com.aidenlauris.gameobjects.Projectile;

public class MachineGun extends Gun {

	public MachineGun() {
		setAtkSpeed(3);
		setQuickRelease(3);
		setLength(20);
		setAccuracy(3);
		setDamage(70);
		setAmmoType(new BulletAmmo().item());
		
		setSpawnSound("machine.wav");
	}

	@Override
	public Projectile bulletType() {
		MachineBullet b = new MachineBullet(getDamage());
		b.setMoveSpeed(60f);
		return b;
	}


	@Override
	public String item() {
		return this.toString();
	}
}
