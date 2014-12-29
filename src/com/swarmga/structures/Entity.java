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
public class Entity extends Location
{

    private boolean live;
    private Colour colour;
    private String name;
    public int distance;
    private int id;

    public Entity(Colour colour, int x, int y)
    {
        super(x, y);
        this.colour = colour;
        this.live = true;
        this.name = "";
        this.distance = 0;
        this.id = -1;
    }
    
    public Entity(Colour colour, int x, int y, int id)
    {
        super(x, y);
        this.colour = new Colour(colour.getR(),
                                colour.getG(),
                                colour.getB());
        this.live = true;
        this.id = id;
        this.name = "";
        this.distance = 0;
    }

    public Entity(String name)
    {
        super(0, 0);
        this.name = name;
    }
    
    public boolean getLiveStatus()
    {
        return live;
    }

    public void setLiveStatus(boolean live)
    {
        this.live = live;
    }

    public Entity(int x, int y)
    {
        super(x, y);
        this.live = true;
        this.colour = new Colour(1.0f, 1.0f, 0.0f);
        this.distance = 0;
        this.name = "";
    }
    
    public Entity(int x, int y, String name, int distance, int id)
    {
        super(x, y);
        this.id = id;
        this.live = true;
        this.colour = new Colour(1.0f, 1.0f, 0.0f);
        this.name = name;
        this.distance = distance;
    }
    
    public Entity(int x, int y, String name, int distance)
    {
        super(x, y);
        this.id = -1;
        this.live = true;
        this.colour = new Colour(1.0f, 1.0f, 0.0f);
        this.name = name;
        this.distance = distance;
    }

    public int getDistance()
    {
        return distance;
    }

    public Colour getColour()
    {
        return colour;
    }

    public String getName()
    {
        return name;
    }   

    public int getId()
    {
        return id;
    } 
    

    @Override
    public String toString()
    {
        return name;
    }
    
}
