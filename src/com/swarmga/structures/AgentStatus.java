/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swarmga.structures;

import com.swarmga.utilities.Util;
import java.util.ArrayList;

/**
 *
 * @author alexgray
 */
public class AgentStatus extends Location
{
    
    private double health;
    private int timeUntilBreedable;
    private boolean breedable;
    private int fertilty;
    ArrayList<Agent> vicinityAgents = new ArrayList<>();
    ArrayList<Entity> vicinityFoods = new ArrayList<>();
    private final int agility;
    private int goalX;
    private int goalY;

    public AgentStatus(int x, int y, int startHealth, int agility, int fertilty)
    {
        super(x, y);
        this.agility = agility;
        this.health = startHealth * 10;
        this.timeUntilBreedable = (int) (500/fertilty);
        this.fertilty = fertilty;
        this.breedable = false;
    }
    
    public void move(int x, int y){
        int dx = x - getX();
        int dy = y - getY();
        //get fair split for x and y amongst agility
        double dRes = (double)this.agility / (Math.abs(dx) + Math.abs(dy));
        //find shared agilty among needed changes
        int zdx = (int) Math.round(Math.abs(dRes * dx) * Util.normalise(dx));
        int zdy = (int) Math.round(Math.abs(dRes * dy) * Util.normalise(dy));
        // slow down when near the goal
        dx = (int) (Math.min(Math.abs(dx), this.agility) * Util.normalise(dx));
        dy = (int) (Math.min(Math.abs(dy), this.agility) * Util.normalise(dy));
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
    }
    
    public void move(Location l){
        move(l.getX(), l.getY());
    }
    
    public void setGoalLocation(Location l){
        System.out.println(l.getX() + " " + l.getY() + "   " + this.getGoalX() + "," + this.getGoalY());
        this.setGoalX(l.getX());
        this.setGoalY(l.getY());
    }
    
    public Location getGoalLocation(){
        return new Location(this.getGoalX(), this.getGoalY());
    }
    
    public boolean atGoalLocation(){
        return this.getGoalX() == this.getX() && this.getGoalY() == this.getY();
    }
    
    public void addVicinityAgents(Agent nearAgent)
    {
        this.vicinityAgents.add(nearAgent);
    }

    public ArrayList<Agent> getVicinityAgents()
    {
        return vicinityAgents;
    }
    
    public void clearVicinityAgents()
    {
        this.vicinityAgents.clear();
    }
    
    public void addVicinityFoods(Entity nearFood)
    {
        this.vicinityFoods.add(nearFood);
    }

    public ArrayList<Entity> getVicinityFoods()
    {
        return vicinityFoods;
    }
    
    public void clearVicinityFoods()
    {
        this.vicinityFoods.clear();
    }

    public int getTimeUntilBreedable()
    {
        return timeUntilBreedable;
    }

    public void decTimeUntilBreedable()
    {
        if(this.breedable == false){
            this.timeUntilBreedable--;
        }        
        if (this.timeUntilBreedable <= 0){
            this.breedable = true;
            this.timeUntilBreedable = (int) (250/this.fertilty);
        }
    }
    
    public void decFoodTUB()
    {
        timeUntilBreedable -= 250;
    }
    
    public int getGoalY()
    {
        return goalY;
    }

    public void setGoalY(int goalY)
    {
        this.goalY = goalY;
    }

    public int getGoalX()
    {
        return goalX;
    }

    public void setGoalX(int goalX)
    {
        this.goalX = goalX;
    }

    public boolean isBreedable()
    {
        return breedable;
    }

    public void breed()
    {
        this.breedable = false;
    }

    public double getHealth()
    {
        return health;
    }

    public void setHealth(double health)
    {
        this.health = health;
    }

    @Override
    public String toString()
    {
        return "   MHP:"+ health + "  TUB:" + timeUntilBreedable + " Regen:" + breedable + super.toString();
    }
    
    
}
