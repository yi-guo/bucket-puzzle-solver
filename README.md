# bucket-puzzle-solver
A Java program to solve the bucket puzzle.

You have a three gallon and a five gallon bucket. What is the minimum number of steps to take
in order to get four gallons of water? Can you give the steps?

What if you have an x gallon and a y gallon bucket, and you want to mix up z gallons of water now?
Is there necessarily a solution all the time for x, y, and z?

This project helps you solve the problem and always gives you the optimized solution, if any. The
project consists of three files:

  1. State.java, which defines a state. For example, the initial state is always (0, 0) indicating
     we have 0 gallons of water in both buckets.
  2. Action.java, which defines an action to be applied to a state. In this problem, we have six
     actions available:
      a) Fill the first bucket, in which case the state goes from (0, 0) to (x, 0)
      b) Fill the second bucket, in which case the state goes from (x, 0) to (x, y)
      c) Empty the first bucket, hence, (x, y) -> (0, y)
      d) Empty the second bucket, hence, (0, y) -> (0, 0)
      e) Pure the water from the first bucket to the second bucket
      f) Pure the water from the second bucket to the first bucket

The idea is to apply the above six actions to the initial state (0, 0), which gives us back six
children (if we don't care about duplicates). Then we continue to apply the six actions to each
child, which forms a tree structure. In this way, we perform breath-first search while expanding
the tree until the target state is found. In this case, we have (z, w) or (w, z) where w can be
any value.

At the same time, we also keep track of those states. If at any time no new state can be generated,
we simply can conclude that x, y, and z do not give a solution.

To run, please complile the three Java files and invoke Search.java. You will be prompted to enter
three integer inputs, and whala!
