/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.swarmga.utilities;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author alexgray
 */
public class Input
{
    
    public static final int NUM_KEYCODES = 256;
    public static final int KEY_ESCAPE          = 0x01;
    public static final int KEY_SPACE           = 0x39;
    public static final int KEY_RETURN          = 0x1C;
    
    private static boolean[] mLastKeys = new boolean[NUM_KEYCODES];
	
        /**
         * Update the positions for all keys
         *
         * @param 
         */
	public static void Update()
	{
            for(int i = 0; i < NUM_KEYCODES; i++)
                mLastKeys[i] = GetKey(i);
	}
	
        /**
         * Return true if key code is down
         *
         * @param 
         */
	public static boolean GetKey(int keyCode)
	{
            return Keyboard.isKeyDown(keyCode);
	}
	
        /**
         * Return true if key code is down and not in mLastKeys
         *
         * @param 
         */
	public static boolean GetKeyDown(int keyCode)
	{
            return GetKey(keyCode) && !mLastKeys[keyCode];
	}
	
        /**
         * Return true if key code is not down and is in mLastKeys
         *
         * @param 
         */
	public static boolean GetKeyUp(int keyCode)
	{
            return !GetKey(keyCode) && mLastKeys[keyCode];
	}
}
