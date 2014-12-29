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
public class Team extends Location
{
    
    private ArrayList<Agent> agents;
    private final AttributeSeed seed;
    private final String team;
    private final Colour colour;
    private int points;
    private int population;
    private String colourString;

    public int getPopulation()
    {
        return population;
    }

    private void updatePopulationSize()
    {
        this.population = this.agents.size();
    }

    public Team(String team, AttributeSeed seed, Location location, Colour colour)
    {
        super(location.getLocation());
        this.colour = colour;
        this.colourString = Util.colourToString(colour);
        this.agents = new ArrayList<>();
        this.seed = seed;
        this.team = team;
        this.points = 0;
    }

    public Team(AttributeSeed seed, Location location, Colour colour)
    {
        super(location.getLocation());
        this.colour = colour;
        this.colourString = Util.colourToString(colour);
        this.agents = new ArrayList<>();
        this.seed = seed;
        this.team = seed.getTeamName();
        this.points = 0;
    }

    public String getTeam()
    {
        return team;
    }

    public AttributeSeed getSeed()
    {
        return seed;
    }
    
    public Colour getColour()
    {
        return colour;
    }
    
    public String getColourString(){
        return colourString;
    }

    public int getPoints()
    {
        return points;
    }

    public void addPoint()
    {
        this.points++;
    }

    public ArrayList<Agent> getAgents()
    {
        return agents;
    }

    public void addAgent(Agent agent)
    {
        this.agents.add(agent);
        agent.setTeam(getName());
        updatePopulationSize();
    }
    
    public void removeDeadAgents()
    {
        ArrayList<Agent> unDeadAgents = new ArrayList<>();
        for (Agent agent : agents)
        {
            if (agent.getHealth() > 0 )
                unDeadAgents.add(agent);
        }
        agents = unDeadAgents;
        updatePopulationSize();
    }

    private String getName()
    {
        return team;
    }
    
    public String overviewString(){
        return team + "  Pop: " + population + "  Score: " + points;
    }

    @Override
    public String toString()
    {
        return this.getSeed().toString() + " " + colourString;
    }
    
    
    
    

}
