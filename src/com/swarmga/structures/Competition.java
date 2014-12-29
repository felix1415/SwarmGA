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
    
    private Team fourTeams(ArrayList<AttributeSeed> seeds){
        Environment env = new Environment(seeds);
        return env.start();
    }
    
    private Team sixtenTeams(ArrayList<AttributeSeed> seeds){
        ArrayList<AttributeSeed> seed = new ArrayList<>();
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
            Environment enviroment = new Environment(set.getSelf());
            
            seeds.add(enviroment.start().getSeed());
        }
        return fourTeams(seeds);
    }
    
    private Team sixtyFourTeams(){
        return null;
    }
    
}
