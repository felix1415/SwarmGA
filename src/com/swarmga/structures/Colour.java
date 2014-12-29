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

    private float r;
    private float g;
    private float b;

    public Colour(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    public Colour()
    {
        this.r = 1.0f;
        this.g = 1.0f;
        this.b = 1.0f;
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
