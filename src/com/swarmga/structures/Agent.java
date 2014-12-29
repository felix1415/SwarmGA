/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.structures;

import com.swarmga.logic.Actions;
import com.swarmga.utilities.Util;
import static com.swarmga.utilities.Util.closestEntity;

/**
 *
 * @author alexgray
 */
public class Agent extends AgentStatus
{

    private final int agility;
    private final int maxHealth;
    private final int attack;
    private final int defense;
    private final int fertility;
    private final int id;
    private Colour colour;
    private String team = "";
    private final boolean valid;
    private final int maxPoints = 15;
    private String state;
    private Agent affectedAgent;
    private Entity affectedFood;
    private Agent attacker;

    public Agent(int id, int maxHealth, int attack, int defense, int agility, int fertility)
    {
        super(0, 0, maxHealth + 1, agility + 1, fertility + 1);
        this.id = id;
        this.agility = agility + 2;
        this.maxHealth = (maxHealth + 1) * 10;
        this.attack = attack + 1;
        this.defense = defense + 1;
        this.fertility = fertility + 1;
        this.state = "Idle";
        this.valid = isValid();
        setGoalLocation(Util.randomLocation());
    }

    public Agent(int id, AttributeSeed aSeed, Colour colour)
    {
        super(0, 0, aSeed.getMaxHealth() + 1,
                aSeed.getAgility() + 2,
                aSeed.getFertility() + 1);
        this.colour = colour;
        this.id = id;
        this.agility = aSeed.getAgility() + 2;
        this.maxHealth = (aSeed.getMaxHealth() + 1) * 10;
        this.attack = aSeed.getAttack() + 1;
        this.defense = aSeed.getDefense() + 1;
        this.fertility = aSeed.getFertility() + 1;
        this.state = "Idle";
        this.valid = isValid();
        setGoalLocation(Util.randomLocation());
    }

    public void update()
    {
        this.decTimeUntilBreedable();
        Entity entity = closestEntity(getVicinityAgents(), getVicinityFoods(), this);
        if (entity == null)
        {
            entity = new Entity("");
        }
        switch (entity.getName())
        {
            case "agent":
                this.state = "Attacking";
                if (Util.getDistance(this, entity) > 5)
                {
                    affectedAgent = null;
                    move(entity.getX() + Util.randomOne() * 5, entity.getY() + Util.randomOne() * 5);
                } else if (Util.getDistance(this, entity) <= 5)
                {
                    affectedAgent = Actions.attack(this);
                    if (affectedAgent == null)
                    {
                        affectedAgent = Actions.attack(this);
                    }
                    move(entity.getX(), entity.getY());
                } else
                {
                    if (atGoalLocation())
                    {
                        setGoalLocation(Util.randomLocation());
                    }
                    move(getGoalLocation());
                }
                break;
            case "food":
                affectedFood = entity;
                this.state = "Going for food";
                if (Util.getDistance(this, entity) <= 5)
                {
                    this.decFoodTUB();
                    setGoalLocation(Util.randomLocation());
                    move(getGoalLocation());
                    break;
                } else if (Util.getDistance(this, entity) <= 100)
                {
                    move(entity.getX(), entity.getY());
                    affectedFood = null;
                } else
                {
                    affectedFood = null;
                    if (atGoalLocation())
                    {
                        setGoalLocation(Util.randomLocation());
                    }
                    move(getGoalLocation());
                }
                break;
            default:
                this.state = "Roaming";
                if (atGoalLocation())
                {
                    setGoalLocation(Util.randomLocation());
                }
                move(getGoalLocation());
                break;
        }
    
        
            
    }

    public void clearAffected()
    {
        affectedAgent = null;
        affectedFood = null;
    }

    public Agent getAffectedAgent()
    {
        return affectedAgent;
    }

    public Entity getAffectedFood()
    {
        return affectedFood;
    }

    public Agent getAttacker()
    {
        return attacker;
    }

    public void setAttacker(Agent attacker)
    {
        this.attacker = attacker;
    }

    private boolean isValid()
    {
        return maxHealth + attack + defense + agility + fertility == 21;
    }

    private int sum()
    {
        return maxHealth + attack + defense + agility + fertility;
    }

    public int getId()
    {
        return id;
    }

    public Colour getColour()
    {
        return colour;
    }

    public void setColour(Colour colour)
    {
        this.colour = colour;
    }

    public int getFertility()
    {
        return fertility;
    }

    public String getTeam()
    {
        return team;
    }

    public void setTeam(String team)
    {
        this.team = team;
    }

    public int getDefense()
    {
        return defense;
    }

    public int getAttack()
    {
        return attack;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getAgility()
    {
        return agility;
    }

    public String lite()
    {
        return "x:" + this.x + "y:" + this.y;
    }

    @Override
    public String toString()
    {
        if (affectedAgent == null)
        {
            affectedAgent = new Agent(0, 0, 0, 0, 0, 0);
        }
        if (affectedFood == null)
        {
            affectedFood = new Entity(0, 0);
        }
        return this.maxHealth + "  " + attack
                + "  " + defense + "  " + agility
                + "  " + fertility + "  id:" + id
                + super.toString() + "T:" + this.getTeam()
                +" | AGENT:"
                + affectedAgent.lite() + " FOOD:"
                + affectedFood.toString()
                + "State: " + state + " R'Goals: ("
                + getGoalX() + "," + getGoalY() + ")";
    }
}
