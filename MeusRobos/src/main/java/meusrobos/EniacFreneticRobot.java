/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meusrobos;

import java.awt.Color;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.DeathEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

/**
 *
 * @author User
 */
public class EniacFreneticRobot extends Robot {
    
    public void run(){
        setColors(Color.YELLOW, Color.BLACK, Color.RED, Color.GRAY, Color.RED);
        
	while(true) {
            setAdjustRadarForGunTurn(false);
            ahead(100);
            turnGunLeft(360);
            back(100);
            turnGunRight(360);
	}        
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) { //Ao achar outro robô
        if(getEnergy() <= 50){
            fire(1);
            back(100);
        }
        else {
            if(getGunHeat() <= 50){
                fire(2);
                back(100);
            }
            else {
                fire(1);
                back(100);
            }
        }
        
        double angulo = event.getBearing(); //Retorna o ângulo do robô adversário
        double distancia = event.getDistance(); //Retorna a distacia do robô adversário
        if (distancia < 200){
            turnGunRight(angulo); 
            fire(3);
        } else {
            turnGunRight(angulo); 
            fire(1);            
        }
    }

    @Override
    public void onBulletHit(BulletHitEvent event) { //Ao acertar o tiro
        fire(3);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) { //Quando for atingido por um tiro
        double angulo = event.getBearing(); //Retorna o ângulo do robô adversário
        
        turnGunRight(angulo); 
        fire(2);        
        
        turnGunRight(angulo - 200); 
        ahead(400);
    }
    
    @Override
    public void onHitRobot(HitRobotEvent event) { //Ao bater em outro robo
        fire(3);         
        double angulo = event.getBearing(); //Retorna o ângulo do robô adversário
        back(300);
        
        turnGunRight(angulo - 190);            
        ahead(150);
        
        turnGunRight(360);
        turnGunRight(angulo);
        fire(2);   
    }    

    @Override
    public void onHitWall(HitWallEvent event) { //Ao bater na parede 
        double anguloParede = event.getBearing(); //Retornar o angulo da parede
        
        
        if(anguloParede == 180){
            turnGunLeft(90);
            ahead(400);
            back(200);
        } else {
            turnGunLeft(150);
            ahead(400);            
            back(200);
        }  
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) { //Ao matar outro robô
        System.out.println("Valeu " + event.getName() + ", mais um pra conta!");
    }

    @Override
    public void onWin(WinEvent event) {
        
    }

    @Override
    public void onDeath(DeathEvent event) {
        
    }

}
