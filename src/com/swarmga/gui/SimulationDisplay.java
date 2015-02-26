/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swarmga.gui;

import com.swarmga.logic.SwarmGA;
import com.swarmga.structures.Agent;
import com.swarmga.structures.Entity;
import com.swarmga.structures.Team;
import java.awt.Font;
import java.util.ArrayList;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author alexgray
 */
public class SimulationDisplay
{

    private TrueTypeFont font;
    private TrueTypeFont title;
    private TrueTypeFont win;
    private String titleString;
    private Team winner;
    private ArrayList<String> data;
    private ArrayList<Agent> agents;
    private ArrayList<Entity> foods;
    private ArrayList<Agent> dead;
    private double timeUntilNextBattle = 3.0;
    
    /**
     * Construct Simulation display
     *
     * @param 
     */
    public SimulationDisplay(String title)
    {
        this.titleString = title;
        try
        {
            Display.setDisplayMode(new DisplayMode(SwarmGA.WIDTH + 300, SwarmGA.HEGIHT));
            Display.setLocation(0, 0);
            Display.create();
            Display.setTitle("Simulation: SwarmGA 0.1 ::  " + title);
        } catch (LWJGLException e)
        {
            System.exit(1);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glDisable(GL_DEPTH_TEST);
        fontInit();
    }

    /**
     * Destroy display
     *
     * @param 
     */
    public void destroy()
    {
        Display.destroy();
    }

    /**
     * Draw all entities on screen
     *
     * @param entity
     * @param size
     */
    private void draw(Entity entity, int size)
    {
        glColor3f(entity.getColour().getR(),
                entity.getColour().getG(),
                entity.getColour().getB());
        glBegin(GL_QUADS);
        {
            glVertex2f((0 - (size / 2)) + entity.getX(), (0 - (size / 2)) + entity.getY());
            glVertex2f((0 + (size / 2)) + entity.getX(), (0 - (size / 2)) + entity.getY());
            glVertex2f((0 + (size / 2)) + entity.getX(), (0 + (size / 2)) + entity.getY());
            glVertex2f((0 - (size / 2)) + entity.getX(), (0 + (size / 2)) + entity.getY());
        }
        glEnd();
    }

    /**
     * Add a thin line for the GUI divider
     *
     * @param 
     */
    private void gui()
    {
        glColor3f(1.0f, 1.0f, 1.0f);
        glBegin(GL_QUADS);
        {
            glVertex2f(1000, 701);
            glVertex2f(1001, 700);
            glVertex2f(1001, 0);
            glVertex2f(1000, 0);
        }
        glEnd();
        renderText(); // text
    }

    /**
     * Draw all entities and pass extra data
     *
     * @param agents
     * @param foods
     * @param dead
     * @param data
     * @param winner
     * @param timeUntilNextBattle
     */
    public void draw(ArrayList<Agent> agents, ArrayList<Entity> foods, 
                     ArrayList<Agent> dead, ArrayList<String> data, Team winner, 
                     double timeUntilNextBattle)
    {
        if(winner != null){
            this.winner = winner;
            this.timeUntilNextBattle = timeUntilNextBattle;
        } else {
            this.data = data;
            this.agents = agents;
            this.foods = foods;
            this.dead = dead;
        }
        glClear(GL_COLOR_BUFFER_BIT);
        for (Entity food : this.foods)
        {
            draw(food, 2);
        }
        for (Agent agent : this.agents)
        {
            draw(new Entity(agent.getColour(), agent.getX(), agent.getY()), 5);
        }

        for (Agent agent : this.dead)
        {
            draw(new Entity(agent.getColour(), agent.getX(), agent.getY()), 40);
        }
        gui(); // frame
        Display.update(); 

        if (Display.isCloseRequested())
        {
            Display.destroy();
            System.exit(0);
        }
    }

    /**
     * Initialise fonts
     *
     * @param 
     */
    public void fontInit()
    {
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        Font awtFontTwo = new Font("Arial", Font.PLAIN, 12);
        Font awtFontThree = new Font("Arial", Font.BOLD, 36);
        title = new TrueTypeFont(awtFont, true);
        font = new TrueTypeFont(awtFontTwo, true);
        win = new TrueTypeFont(awtFontThree, true);
    }

    /**
     * Render all text on screen
     *
     * @param 
     */
    public void renderText()
    {
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        //draw winner text on screen
        if(winner != null){
            win.drawString(375, 375, "Winner is " + this.winner.getTeam() + "/" +
                                                    this.winner.getColourString(), Color.yellow);
            win.drawString(375, 200, "Next battle in: " +
                                        this.timeUntilNextBattle, Color.yellow);
        }
        //display agent seeds on screen from enviroment    
        int lineVal = 50;
        int lineNum = 0;
        for (String string : this.data)
        {
            font.drawString(1020, lineVal, string, Color.white);
            lineNum++;
            lineVal += 15;
        }
        title.drawString(1050, 20, this.titleString, Color.white);
        glDisable(GL_TEXTURE_2D);
    }

}
