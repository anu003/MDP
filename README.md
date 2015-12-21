
###Reinforcement Learning - Markov Decision Process (MDP)

In this assignment, I tested two model-based planning algorithms (value iteration and policy iteration) and one model-free learning algorithm (Q-learning). To demonstrate the different properties of these algorithms, I randomly created two grid worlds MDPs and solve them using these three algorithms separately. 

The experiments showed that policy iteration has better performance with small size MDP problem whereas value iteration converge much faster than policy iteration when it comes to large MDP problem. Compared to value iteration or policy iteration, the experiments demonstrated that Q-learning takes much longer time to find a solution for both small size and large size MDPs.

The algorithms are provided by BURLAP 2 library:
http://burlap.cs.brown.edu/
