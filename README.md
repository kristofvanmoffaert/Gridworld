# Gridworld
Gridworld (reinforcement learning)

While pursuing my Phd in Machine Learning I implemented some Java applets for educational purposes. 
I created a toolbox where students could play with different reinforcement learning algorithms. Reinforcement 
learning is a branch of machine learning where the focus lies on learning from interaction. Reinforcement learning can be 
seen as the learning process that automatically takes place in people's minds while doing a task for the first time. 
Similar to how humans behave, the reinforcement learning algorithm tries out different actions and tries to build
a knowledge base that comprises the gathered wisom. As time progresses and more actions are tried, the algorithm
has quite a good understanding of how the task can be solved and how the dynamics in the environment influences these actions. 
In the end, the algorithm will converge to the optimal policy which can, from that point on, be exploited. 

In this applet, I implemeted:
- An interactive environment where obstacles can be added and removed
- Different goal locations with different rewards
- A diverse set of reinforcement learning algorithm ranging from Q-leanring, Q-lambda, Sarsa
- The user can also
  - inspect the learning process every episode
  - at the end of learning
  - perform action selection manually and inspect the impact
  
  A screenshot of the app can be found below:
  
  ->![alt text](https://raw.githubusercontent.com/kristofvanmoffaert/Gridworld/master/grid.png)<-
