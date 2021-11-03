# Test Plan
## Unit Test
### Cell (the smallest unit of a maze)
- initiate with correct relationship with neighbors 
- 
### Perfect Maze (the basis of all mazes)
- print method
- form a perfect maze with expected num of rows and cols
- generate expected amount of gold (from abstract class)
- generate expected amount of thieves (from abstract class)

### Wrapped Room Maze
- print method
- form a wrapped room maze with expected num of rows and cols
- can work through edge walls

### Unwrapped Room Maze
- print method
- form an unwrapped room maze with expected num of rows and cols
- not a perfect maze

## Integration Test
### Driver Class
- Can start: interact with users to set up maze (while checking illegal response)
- Can be played 
- Cannot move towards wall
- Can interact
- Can end