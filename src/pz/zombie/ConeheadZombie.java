package pz.zombie;

import java.util.ArrayList;

import org.newdawn.slick.Animation;

import com.Position;

import gui.AnimationLoader;
import pz.Bullet;
import pz.Plant;
import pz.Zombie;

public class ConeheadZombie extends Zombie {

    private static int hp = 200;
    private static int damage = 20;
    private static int attackInterval = 100;
    private static float speed = 0.4f;

    private Animation walkAni;
    private Animation attackAni;

    public ConeheadZombie(Position pos) {
        super("ConeheadZombie", hp, damage, attackInterval, speed, pos);
        walkAni = getAnimation();
        attackAni = AnimationLoader.getAnimationFromFolder("res/ZombieTest/ConeheadZombie/attack", 110);
    }

    @Override
    public void move() {
        setPos(getPos().x - getSpeed(), getPos().y);
    }

    @Override
    protected void loadAnimation() {
        setAnimation(AnimationLoader.getAnimationFromFolder("res/ZombieTest/ConeheadZombie/walk", 110));
    }

    @Override
    public void attack(Plant[][] plant, ArrayList<Bullet> bulletList) {
        boolean hit = false;
        for (int i=0; i<5; i++) {
            for (int j=0; j<9; j++) {
                if (plant[i][j] != null) {
                    if (Position.isInteract(this, plant[i][j])) {
                        hit = true;
                        setSpeed(0);
                        setAnimation(attackAni);
                        if (getFramePassed() >= getAttackInterval()) {
                            plant[i][j].setHp(plant[i][j].getHp() - getDamage());
                            setFramePassed(0);
                        }
                        setFramePassed(getFramePassed()+1);
                        return;
                    }
                }
            }
        }
        if (hit == false) {
            setAnimation(walkAni);
            setSpeed(speed);
        }
    }

}
