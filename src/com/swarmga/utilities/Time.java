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
public class Time
{

    private static final long SECOND = 1000000000L;

    public static double getTime()
    {
        return (double) (System.nanoTime() / (double) SECOND);
    }
    
    

}
