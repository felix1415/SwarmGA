# SwarmGA

The original aim for this project was to implement Swarm and Genetic Algorithm into a neat eviroment with different teams, attributes and rules. It's come out as a simpler, loosely based Genetic algorithm with no Swarm capabilties (so far (arguably none...)).


4 Teams are present. Each team has 50 'agents', of which 10 start in the middle 'death zone' of the map. Each of the 40 remaining team agents start in one of the 4 corners on the map. Each team has a set of attributes which have to add up to 15, these are;


  -Health    : (+1 and multiplied by 10 for the final value) : How much damage an agent can take before death
  
  -Attack    : (+1 for final value) : How hard an agent can attack
  
  -Defense   : (+1 for final value) : How resilient the agent is to attack
  
  -Agility   : (+2 for final value) : How fast the agent can move
  
  -Fertility : (+1 for final value) : How long it takes to either replenish health or reproduce another agent
  
  
If 2 teams remain in the ring with a population over 400, a 'face-off' is triggered which means the two teams, with 50 agents each, travel towards each other from different sides of the enviroment to face-off. This usually quickly decides a winner to either win outright, or move to the next round.

  
You can create '16 team competitions' that has 4 rounds of 4 teams, and a final round of each of the 4 winners from the 4 previous rounds.


![Alt text](http://i.imgur.com/ZzYdlRF.png "Round")
