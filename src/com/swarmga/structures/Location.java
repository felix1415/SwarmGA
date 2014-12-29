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
public class Location
{
    
    int x;
    int y;

    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Location(Location location)
    {
        this.x = location.getX();
        this.y = location.getY();
    }
    
    public Location getLocation()
    {
        return this;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public String toString()
    {
        return ", x=" + x + ", y=" + y;
    }
    
    

}
