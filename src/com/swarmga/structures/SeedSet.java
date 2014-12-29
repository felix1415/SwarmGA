/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swarmga.structures;

import java.util.ArrayList;

/**
 *
 * @author alexgray
 */
public class SeedSet
{

    private final ArrayList<AttributeSeed> seeds = new ArrayList<>();

    public ArrayList<AttributeSeed> getSelf()
    {
        return seeds;
    }

    public void addSeed(AttributeSeed seed)
    {
        this.seeds.add(seed);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(); 
        for (AttributeSeed seed : seeds)
        {
            sb.append(seed.toString() + '\n');
        }
        
        return sb.toString();
    }
    
    
    
}
