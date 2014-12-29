/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swarmga.utilities;

/**
 *
 * @author alexgray
 */
public class IDgenerator
{
    
    private int id = 0;

    public int getId()
    {
        this.id++;
        return id;
    }

}
