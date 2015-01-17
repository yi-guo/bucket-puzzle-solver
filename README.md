# bucket-puzzle-solver
A Java program to solve the bucket puzzle.

You have a three gallon and a five gallon bucket. What is the minimum number of steps to take
in order to get four gallons of water? Can you give the steps?

What if you have an x gallon and a y gallon bucket, and you want to mix up z gallons of water now?
Is there necessarily a solution all the time for x, y, and z?

This project helps you solve the problem and always gives you the optimized solution, if any.
The idea is to apply the six possible actions to each of the state starting at (0, 0) indicating
there is no water yet in either bucket.

Generate a search tree and conduct breadth-first search until the target state is found. Or until no
no state can be generated, which means there is no solution to the given x, y, and z.

Complile the three Java files and run Search.java. You will be prompted three integer inputs, and whala!
