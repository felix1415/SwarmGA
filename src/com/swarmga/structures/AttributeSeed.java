/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.structures;

/**
 *
 * @author alexgray
 */
public class AttributeSeed
{

    private final int agility;
    private final int maxHealth;
    private final int attack;
    private final int defense;
    private final int fertility;
    private int seedId;
    private int[] values = new int[5];
    private String teamName;

    /**
     * Get the seeds team name
     * @return teamName
     */
    public String getTeamName()
    {
        return teamName;
    }

    /**
     * Set the seeds team name
     *@param teamName
     */
    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }
    
    /**
     * Construct a random seed
     *
     */
    public AttributeSeed()
    {
        generateValues();
        this.agility = values[0];
        this.maxHealth = values[1];
        this.attack = values[2];
        this.defense = values[3];
        this.fertility = values[4];
    }
    
    /**
     * Construct a seed from values
     *
     */
    public AttributeSeed(int mHP, int att, int def, int agl, int fer)
    {
        this.maxHealth = mHP;
        this.attack = att;
        this.defense = def;
        this.agility = agl;
        this.fertility = fer;
    }
    
    /**
     * Return the seed ID
     *
     * @return seedId
     */
    public int getSeedId()
    {
        return seedId;
    }
    
    /**
     * Set the seed ID
     *
     * @param seedId
     */
    public void setSeedId(int seedId)
    {
        this.seedId = seedId;
    }

    /**
     * Return this seed
     * @return this
     */
    public AttributeSeed getSeed()
    {
        return this;
    }

    /**
     * Randomly generate values for the seed
     *
     */
    private void generateValues()
    {
        int sum = 0;
        int i = 0;
        while (true) //while not all 15 points have been used
        {
            this.values[i] = (int) (Math.random() * 8.0);
            if (i == 4) // loop over values[i]
                i = 0;
            i++;
            if (sumOfValues() == 15) // until all 15 points have been used
            {
                break;
            }

        }
    }
    
    /**
     * Get the sum of all the values for attributes
     * @return value
     */
    private int sumOfValues()
    {
        int value = 0;
        for (int i = 0; i < 5; i++)
        {
            value += values[i];
        }
        return value;
    }

    public int getAgility()
    {
        return agility;
    }
    
    public String getAgilityString()
    {
        return "" +agility;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }
    
    public String getMaxHealthString()
    {
        return "" + maxHealth;
    }

    public int getAttack()
    {
        return attack;
    }
    
    public String getAttackString()
    {
        return "" +attack;
    }

    public int getDefense()
    {
        return defense;
    }
    
    public String getDefenseString()
    {
        return "" +defense;
    }

    public int getFertility()
    {
        return fertility;
    }
    
    public String getFertilityString()
    {
        return "" +fertility;
    }

    /**
     * Returns a string of an AttributeSeed
     * @return toString
     */
    @Override
    public String toString()
    {
        return  maxHealth + "    " 
                + attack + "    " 
                + defense + "    " 
                + agility + "    " 
                + fertility + "    " 
                + seedId;
    }

    
    
    

}
