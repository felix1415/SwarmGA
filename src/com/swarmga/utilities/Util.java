/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.utilities;

import com.swarmga.structures.Agent;
import com.swarmga.structures.Colour;
import com.swarmga.structures.Entity;
import com.swarmga.structures.Location;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author alexgray
 */
public class Util
{

    public static int getDistance(int x, int y, int dx, int dy)
    { // return distance between two points
        double xdist = pow(((double) x - (double) dx), 2.0);
        double ydist = pow(((double) y - (double) dy), 2.0);
        return (int) pow((xdist + ydist), 0.5);
    }

    public static double normalise(double num)
    {
        return Math.abs(num) / num;
    }

    public static int randomOne()
    {
        int num = (int) (Math.round(Math.random() * 1));
        if (num == 0)
        {
            return 1;
        } else
        {
            return -1;
        }

    }

    public static int getDistance(Agent agentOne, Agent agentTwo)
    { // return distance between two agents
        return getDistance(agentOne.getX(), agentOne.getY(), agentTwo.getX(), agentTwo.getY());
    }

    public static int getDistance(Agent agent, Entity food)
    {
        return getDistance(agent.getX(), agent.getY(), food.getX(), food.getY());
    }

    public static boolean isSameTeam(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getTeam().equals(agentTwo.getTeam());
    }

    public static boolean isMateFound(Agent agentOne, Agent agentTwo)
    {
        return agentOne.isBreedable() & agentTwo.isBreedable() & isSameTeam(agentOne, agentTwo);
    }

    public static boolean higherDefence(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getDefense() > agentTwo.getDefense();
    }

    public static boolean higherAttack(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getAttack() > agentTwo.getAttack();
    }

    public static boolean higherAgility(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getAgility() > agentTwo.getAgility();
    }

    public static boolean higherHealth(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getHealth() > agentTwo.getHealth();
    }

    public static boolean higherAttackThanDefense(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getAttack() > agentTwo.getDefense();
    }

    public static boolean higherDefenseThanAttack(Agent agentOne, Agent agentTwo)
    {
        return agentOne.getDefense() > agentTwo.getAttack();
    }

    public static boolean multiplier(double agiltyDif)
    {
        return agiltyDif > Math.random();
    }

    private static double attack(int attack, int defense, int attAgility, int defAgility)
    {
        //agility difference decimal for chance of  multiplier
        double agilityDif = ((double) Math.abs(attAgility - defAgility)) / 10;
        if (attAgility > defAgility)
        {
            if (attack > defense)
            {
                if (multiplier(agilityDif))
                {
                    return ((attack - defense) * 2) + 0.5;
                } else
                {
                    return ((attack - defense) * 1.75) + 0.5;
                }
            } else if (attack < defense)
            {
                if (multiplier(agilityDif))
                {
                    return (defense - attack) + 0.5;
                } else
                {
                    return ((defense - attack) * 0.5) + 0.5;
                }
            } else
            {
                return 0.5;
            }
        } else if (defAgility == attAgility)
        {
            if (attack > defense)
            {
                return ((attack - defense) * 1.5) + 0.5;
            } else if (attack < defense)
            {
                return ((defense - attack) * 0.5) + 0.5;
            } else
            {
                return 0.5 + 0.5;
            }
        } else if (defAgility > attAgility)
        {
            if (attack > defense)
            {
                if (multiplier(agilityDif))
                {
                    return 0 + 0.5;
                } else
                {
                    return ((attack - defense) * 1.5) + 0.5;
                }
            } else if (attack < defense)
            {
                if (multiplier(agilityDif))
                {
                    return 0 + 0.5;
                } else
                {
                    return 0.35 + 0.5;
                }
            } else
            {
                if (multiplier(agilityDif))
                {
                    return 0 + 0.5;
                } else
                {
                    return 0.5 + 0.5;
                }
            }
        } else
        {
            return 0;
        }
    }

    public static Entity closestEntity(ArrayList<Agent> agents, ArrayList<Entity> foods, Agent host)
    {
        ArrayList<Entity> entities = new ArrayList<>();
        for (Agent agent : agents)
        {
            if (!isSameTeam(agent, host))
            {
                entities.add(new Entity(agent.getX(), agent.getY(), "agent",
                        getDistance(agent.getX(), agent.getY(), host.getX(), host.getY())));
            }
        }
        for (Entity food : foods)
        {
            entities.add(new Entity(food.getX(), food.getY(), "food",
                    getDistance(food.getX(), food.getY(), host.getX(), host.getY()), food.getId()));
        }
        entities = sortDistances(entities);
        if (entities.size() > 0)
        {
            return entities.get(0);
        } else
        {
            return null;
        }
    }

    public static ArrayList sortDistances(ArrayList<Entity> entities)
    {
        Collections.sort(entities, new Comparator<Entity>()
        {

            @Override
            public int compare(Entity o1, Entity o2)
            {
                return Integer.compare(o1.getDistance(), o2.getDistance());
            }

        });
        return entities;
    }

    public static Agent agentOneAttackAgentTwo(Agent agentOne, Agent agentTwo)
    {
        double dHealth = attack(agentOne.getAttack(), agentTwo.getDefense(),
                agentOne.getAgility(), agentTwo.getAgility());
        agentTwo.setHealth((int) Math.round(agentTwo.getHealth() - dHealth));
        agentTwo.setAttacker(agentOne);
        return agentTwo; //return affected agent
    }

    public static Location randomLocation()
    {
        return new Location((int) (Math.random() * 1000), (int) (Math.random() * 700));
    }

    public static void saveCurrentState()
    {
        //save current enviroment to file
    }

    public static double round(double value, int places)
    {
        if (places < 0)
        {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String colourToString(Colour colour)
    {
        Colour red = new Colour(1.0f, 0.0f, 0.0f);
        Colour cyan = new Colour(0.0f, 1.0f, 1.0f);
        Colour blue = new Colour(0.0f, 0.0f, 1.0f);
        Colour pink = new Colour(1.0f, 0.0f, 1.0f);
        if (colour.getR() == blue.getR() && colour.getG() == blue.getG() && colour.getB() == blue.getB())
        {
            return "Blue";
        } else if (colour.getR() == red.getR() && colour.getG() == red.getG() && colour.getB() == red.getB())
        {
            return "Red";
        } else if (colour.getR() == cyan.getR() && colour.getG() == cyan.getG() && colour.getB() == cyan.getB())
        {
            return "Cyan";
        } else if (colour.getR() == pink.getR() && colour.getG() == pink.getG() && colour.getB() == pink.getB())
        {
            return "Pink";
        }
        return "";

    }

}
