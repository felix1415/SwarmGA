/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.logic;

import com.swarmga.gui.Setup;

/**
 *
 * @author alexgray
 */
public class SwarmGA
{

    public static final int WIDTH = 1000;
    public static final int HEGIHT = 700;
    public static final int FRAME_RES = 25;

    public static void main(String[] args)
    {
        new SwarmGA();
    }

    public SwarmGA()
    {
        new Setup().setVisible(true); //start setup
    }

}