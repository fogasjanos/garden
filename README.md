Garden
======

## The problem
You have a rectangular orchard full of apple trees. It is represented as a two-dimensional int array.
Each element represents how much apple we can harvest from there.
The goal to collect the most apples you can by walking from the bottom left corner to the top right corner.
You can only go either up or to the right.

For example:
```text
[[1, 4, 2],
 [2, 3, 1],
 [0, 3, 0]]
```
The max number of apples you can collect is 12. (`Rigth +3, Up +3, Up +4, Right +2`).

Implement the following methods:
1. The `harvest` method which returns with the maximum number of fruits you can collect from the input orchard.
```java
public int harvest(int[][] orchard);
```

2. Let's assume you have two magical tokens. Those can be used once per square and doubles the fruits there.
```java
public int harvestWithTokens(int[][] orchard);
```

**Note:** `0 <= orchard[x][y]`

## Development

### Requirements

- Java 11+

### Checkout
`git clone https://github.com/fogasjanos/orchard.git`

### Build
`gradlew build`

### Logs
`logs/orchard*.log`
