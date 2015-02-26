/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swarmga.structures;

import com.swarmga.gui.Setup;
import com.swarmga.logic.Environment;
import java.util.ArrayList;

/**
 *
 * @author alexgray
 */
public class Competition
{

    /**
     * Construct a competition from Attribute seeds and the setup display
     * @param seeds
     * @param setup
     *
     */
    public Competition(ArrayList<AttributeSeed> seeds, Setup setup) //single
    {
        setup.dispose(); // kill setup window
        Team winner;
        if(seeds.size() == 4){
            winner = fourTeams(seeds);
        } else if (seeds.size() == 16){
            winner = sixtenTeams(seeds);
            System.out.println(winner.toString());
        } else if (seeds.size() == 32){
            //none
        }
    }
    
    /**
     * Return the winner of a four way battle
     * @param seeds
     * @return winner
     *
     */
    private Team fourTeams(ArrayList<AttributeSeed> seeds){
        Environment env = new Environment(seeds, "Four Team Battle");
        return env.start();
    }
    
    /**
     * Return the winner of a 16 way battle
     * @param seeds
     * @return winner
     *
     */
    private Team sixtenTeams(ArrayList<AttributeSeed> seeds){
        ArrayList<AttributeSeed> seed = new ArrayList<>(); // possibly redundant?
        ArrayList<SeedSet> sets = new ArrayList<>();
        int num = 0;
        sets.add(0, new SeedSet());
        sets.add(1, new SeedSet());
        sets.add(2, new SeedSet());
        sets.add(3, new SeedSet());
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                sets.get(j).addSeed(seeds.get(num));
                num++;
            }
        }
        seeds = new ArrayList<>();
        for (SeedSet set : sets)
        {
            int heatNum = sets.indexOf(set) + 1;
            Environment enviroment = new Environment(set.getSelf(), "Round "+heatNum + " Battle");
            
            seeds.add(enviroment.start().getSeed());
        }
        return fourTeams(seeds);
    }
    
    /**
     * Return the winner of a 64 way battle
     * @param seeds
     * @return winner
     * @exception NOT WRITTEN
     *
     */
    private Team sixtyFourTeams(){
        return null;
    }
    
}
