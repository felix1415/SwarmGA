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

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }
    
    public AttributeSeed()
    {
        generateValues();
        this.agility = values[0];
        this.maxHealth = values[1];
        this.attack = values[2];
        this.defense = values[3];
        this.fertility = values[4];
    }
    
    public AttributeSeed(int mHP, int att, int def, int agl, int fer)
    {
        this.maxHealth = mHP;
        this.attack = att;
        this.defense = def;
        this.agility = agl;
        this.fertility = fer;
    }

    public int getSeedId()
    {
        return seedId;
    }

    public void setSeedId(int seedId)
    {
        this.seedId = seedId;
    }

    public AttributeSeed getSeed()
    {
        return this;
    }

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
