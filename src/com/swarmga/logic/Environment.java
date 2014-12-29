/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.logic;

import com.swarmga.utilities.*;
import com.swarmga.gui.*;
import com.swarmga.structures.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author alexgray
 */
public class Environment
{
    private final double DEBOUNCE = 0.02;
    //Teams
    private final ArrayList<Team> teams = new ArrayList<>();
    //ID's
    private final IDgenerator teamID = new IDgenerator();
    private final IDgenerator agentID = new IDgenerator();
    //Spawns
    private final ArrayList<Location> spawns = new ArrayList<>();
    //Team colours
    private final ArrayList<Colour> colours = new ArrayList<>();
    //Food
    private final ArrayList<Entity> foods = new ArrayList();
    private ArrayList<Entity> alteredFood = new ArrayList<>();
    //Agents
    private ArrayList<Agent> agents = new ArrayList<>();
    private ArrayList<Agent> alteredAgent = new ArrayList<>();
    private ArrayList<Agent> deadAgents = new ArrayList<>();
    //UI and Controls
    private final SimulationDisplay display;
    private boolean running;
    private boolean pause;
    private double timeUntilNextBattle;
    
    /**
     * Construct Environment with four teams from seeds
     *
     * @param ArrayList<AttributeSeed> seeds
     */
    public Environment(ArrayList<AttributeSeed> seeds)
    {
        //add default spawns
        initData();
        addTeams(seeds);
        addInitAgents();
        addInitFood();
        this.running = true;
        this.pause = false;
        this.display = new SimulationDisplay();
        this.timeUntilNextBattle = 3.0;
    }
    /**
     * Start Environment simulator
     *
     * @param
     */
    public Team start()
    {
        this.mainLoop();
        this.display.destroy();
        return isEnd();
    }
    /**
     * Add initial agents to Environment
     *
     * @param
     */ 
    private void addInitAgents()
    {
        // add new agents to team
        for (Team team : teams)
        {
            for (int i = 0; i < 50; i++)
            {
                team.addAgent(new Agent(agentID.getId(), team.getSeed(), team.getColour()));
            }
        }
        // add agents to agent list
        for (Team team : teams)
        {
            for (Agent agent : team.getAgents())
            {
                this.agents.add(agent);
                agent.setX(((int) (Math.random() * 20) - 10) + team.getX());
                agent.setY(((int) (Math.random() * 20) - 10) + team.getY());
                if (team.getAgents().indexOf(agent) % 5 == 0)
                {
                    agent.setX(((int) (Math.random() * 300) - 150) + 500);
                    agent.setY(((int) (Math.random() * 300) - 150) + 350);
                }
                agent.setHealth(agent.getMaxHealth());
            }
        }
    }
    /**
     * Update Environment
     *
     * @param
     */ 
    public void update() //MAIN UPDATE FUNCTION
    {
        this.findCloseAgenets();
        this.findCloseFoods();

        if (this.isEnd() == null)
        {
            this.breed();
            this.agentUpdate();
            this.interactionUpdate();
        }

        this.removeDeadAgents();
        display.draw(agents, foods, deadAgents, this.toGuiString(),
                this.isEnd(), Util.round(timeUntilNextBattle, 2));
    }
    /**
     * Update all agents in Environment
     *
     * @param
     */ 
    private void agentUpdate()
    {
        for (Agent agent : agents)
        {
            agent.update();
        }
    }
    /**
     * Main loop for updates with timing and input
     *
     * @param
     */ 
    private void mainLoop()
    {
        while (running)
        {

            double frameTime = 1.0 / SwarmGA.FRAME_RES;
            double frameCounter = 0;
            double lastTime = Time.getTime();
            double unprocessedTime = 0;
            int frames = 0;
            
            while (running)
            {
                double startTime = Time.getTime();
                double passedTime = startTime - lastTime;
                lastTime = startTime;
                unprocessedTime += passedTime;
                frameCounter += passedTime;
                
                while (unprocessedTime > frameTime)
                {
                    
                    unprocessedTime -= frameTime;
                    if (this.isEnd() != null)
                    {
                        this.timeUntilNextBattle -= frameTime;
                        if (this.timeUntilNextBattle < 0)
                        {
                            this.running = false;
                        }
                    }
                    if (frameCounter >= 1.0)
                    {
                        //every second
                        frames = 0;
                        frameCounter = 0;
                    }
                    frames++; //**Every 10th Second**
                    
                    if(!pause){
                        this.update();
                    }
                    
                    
                }
                if (Input.GetKey(Input.KEY_ESCAPE))
                    {
                        this.display.destroy();
                    }
                    
                    if (Input.GetKey(Input.KEY_SPACE))
                    {
                        this.pause = false;
                    }
                    if (Input.GetKey(Input.KEY_RETURN))
                    {
                        this.pause = true;
                        System.out.println("unpause");
                    }
                Input.Update(); // update input
            }
            if (this.timeUntilNextBattle < 0)
            {
                this.display.destroy();
                break;
            }
        }
    }

    private void findCloseAgenets()
    {
        Collections.shuffle(agents);
        for (Agent agent : agents)
        {
            agent.clearVicinityAgents();
            for (Agent otherAgent : agents)
            { // for every agent against every other agent
                if (agent.getId() != otherAgent.getId()) // if not same agent then
                {
                    if (Util.getDistance(agent, otherAgent) <= 100)
                    {
                        agent.addVicinityAgents(otherAgent);
                    }
                }
            }
        }
    }

    private void findCloseFoods()
    {
        for (Agent agent : agents)
        {
            agent.clearVicinityFoods();
            for (Entity food : foods)
            { // for every food, if in range, add to foods list
                if (Util.getDistance(agent, food) <= 100)
                {
                    agent.addVicinityFoods(food);
                }
            }
        }
    }

    private void interactionUpdate()
    {
        for (Agent agent : agents)
        {
            if (agent.getAffectedAgent() != null)
            { //add all altered agents to list
                this.alteredAgent.add(agent.getAffectedAgent());
            }
            if (agent.getAffectedFood() != null)
            { //add all altered agents to list
                this.alteredFood.add(agent.getAffectedFood());
            }
            agent.clearAffected();
        }

        for (Agent affected : alteredAgent)
        {
            for (Agent agent : agents)
            {
                if (affected.getId() == agent.getId())
                {
                    agent.setHealth(affected.getHealth());
                }
            }
        }
        alteredAgent = new ArrayList<>();

        for (Entity affected : alteredFood)
        {
            for (Entity food : foods)
            {
                if (affected.getId() == food.getId())
                {
                    food.setX((int) (Math.random() * 1000));
                    food.setY((int) (Math.random() * 700));
                    alteredFood.remove(food);
                }
            }
        }
        alteredFood = new ArrayList<>();
    }

    private void addTeams(ArrayList<AttributeSeed> seeds)
    {
        //take in seeds from setup
        for (AttributeSeed seed : seeds)
        {
            int id = teamID.getId();
            if (seed.getTeamName().equals("To Be Assigned"))
            {
                seed.setTeamName(Integer.toString(id));
                seed.setSeedId(id);
                //team set up with seed and spawn, indexed order by seeds=spawns
                this.teams.add(new Team(seed,
                        spawns.get(seeds.indexOf(seed)),
                        colours.get(seeds.indexOf(seed))));
            } else
            {
                seed.setSeedId(id);
                //team set up with seed and spawn, indexed order by seeds=spawns
                this.teams.add(new Team(seed,
                        spawns.get(seeds.indexOf(seed)),
                        colours.get(seeds.indexOf(seed))));
            }
        }
    }

    private void initData()
    {
        this.spawns.add(0, new Location(25, 25));
        this.spawns.add(1, new Location(975, 25));
        this.spawns.add(2, new Location(25, 675));
        this.spawns.add(3, new Location(975, 675));
        this.colours.add(0, new Colour(1.0f, 0.0f, 0.0f));
        this.colours.add(1, new Colour(0.0f, 1.0f, 1.0f));
        this.colours.add(2, new Colour(0.0f, 0.0f, 1.0f));
        this.colours.add(3, new Colour(1.0f, 0.0f, 1.0f));
    }

    private void addInitFood()
    {
        for (int i = 0; i < 10; i++)
        {
            foods.add(new Entity(new Colour(1.0f, 1.0f, 0.0f),
                    ((int) (Math.random() * 1000)),
                    ((int) (Math.random() * 700)), (i + 1)));
        }
    }

    private void addPoint(String team)
    {
        for (Team t : teams)
        {
            if (t.getTeam().equals(team))
            {
                t.addPoint();
                return;
            }
        }
    }

    private Agent breedNewAgentFrom(Agent agent)
    {
        for (Team team : teams)
        {
            if (team.getTeam().equals(agent.getTeam()))
            {
                Agent newAgent = new Agent(agentID.getId(), team.getSeed(), team.getColour());
                newAgent.setX(((int) (Math.random() * 10) - 5) + agent.getX());
                newAgent.setY(((int) (Math.random() * 10) - 5) + agent.getY());
                newAgent.setHealth(agent.getMaxHealth());
                if (Math.random() < 0.15)
                {
                    team.addAgent(newAgent);
                    return newAgent;
                } else
                {
                    return null;
                }

            }
        }
        return null;
    }

    private void breed()
    {
        ArrayList<Agent> newAgents = new ArrayList<>();
        for (Agent agent : agents)
        {
            //breed or regen
            if (agent.isBreedable() == true)
            {
                if (agent.getHealth() > agent.getMaxHealth() / 2)
                {
                    Agent newAgent = breedNewAgentFrom(agent);
                    if (newAgent != null)
                    {
                        newAgents.add(newAgent);
                    }
                } else
                {
                    agent.setHealth(agent.getMaxHealth());
                }
                agent.breed();

            }
        }
        for (Agent agent : newAgents)
        {
            this.agents.add(agent);
        }
    }

    private void removeDeadAgents()
    {
        ArrayList<Agent> unDeadAgents = new ArrayList<>();
        deadAgents = new ArrayList<>();
        for (Agent agent : agents)
        { //update moves for all agents
            if (agent.getHealth() > 0)
            {
                unDeadAgents.add(agent);
            } else
            {
                deadAgents.add(agent);
                addPoint(agent.getAttacker().getTeam());
            }
        }
        agents = unDeadAgents;
        for (Team team : teams)
        {
            team.removeDeadAgents();
        }
    }

    private Team isEnd()
    {
        int liveTeams = 0;
        Team winner = null;
        for (Team team : teams)
        {
            if (team.getPopulation() > 0)
            {
                liveTeams++;
                winner = team;
            }
        }
        if (liveTeams == 1)
        {
            return winner;
        }
        return null;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Team team : teams)
        {
            sb.append(team.toString()).append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    private ArrayList<String> toGuiString()
    {
        ArrayList<String> string = new ArrayList<>();
        for (Team team : teams)
        {
            string.add(team.overviewString());
            string.add("Colour:   " + team.getColourString());
            string.add("Health:   " + (team.getSeed().getMaxHealth()+1)*10);
            string.add("Attack:   " + (team.getSeed().getAttack()+1));
            string.add("Defence:  " + (team.getSeed().getDefense()+1));
            string.add("Agility:  " + (team.getSeed().getAgility()+2));
            string.add("Fertility: " + (team.getSeed().getFertility()+1));
            string.add("");
        }
        return string;
    }
}
