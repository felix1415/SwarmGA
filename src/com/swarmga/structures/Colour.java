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
public class Colour
{

    private final float r;
    private final float g;
    private final float b;

    /**
     * Construct a colour
     * @param r - red
     * @param g - green
     * @param b - blue
     *
     */
    public Colour(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public float getB()
    {
        return b;
    }

    public float getG()
    {
        return g;
    }

    public float getR()
    {
        return r;
    }
    
    @Override
    public String toString()
    {
        return r+g+b+"";
    }
    
    

}
