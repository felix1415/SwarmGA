/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.logic;

import com.swarmga.structures.Agent;
import com.swarmga.utilities.Util;
import java.util.ArrayList;

/**
 * UNUSED CLASS FOR POSSIBLE FUTURE DEVELOPMENT
 * @author alexgray
 */
public class Actions
{

    public static void example(Agent agent, ArrayList<Agent> agents)
    {
        //stay 10 away from other agents
        //attack when their attack is lower
        //attack when their deffence is lower
        //attack when they are slower
    }

    public static Agent attack(Agent agent)
    {
        Agent target = new Agent(0, 3, 3, 3, 3, 3);
        target.setX(2000);
        for (Agent other : agent.getVicinityAgents())
        {
            if (!Util.isSameTeam(other, agent))
            {
                if (Util.getDistance(other, agent) < Util.getDistance(target, agent))
                {
                    target = other;
                }
            }
        }

        if (agent != target && target.getId() != 0)
        { //attack interaction
            if (Util.getDistance(agent, target) <= 100)
            {
                return Util.agentOneAttackAgentTwo(agent, target); //return affected agent
            } else
            {
                return null;
            }
        } else
        {
            return null;
        }

    }
}
