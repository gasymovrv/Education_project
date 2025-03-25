n = int(input())
matrix = [[0 for _ in range(n)] for _ in range(n)]

# Define the initial value
value = 1

# Define the initial boundaries of the spiral
top = 0
bottom = n - 1
left = 0
right = n - 1

while True:
    # Check if the spiral is complete
    if left > right or top > bottom:
        break

    # Top row: Move right
    for k in range(left, right + 1):
        matrix[top][k] = value
        value += 1
    top += 1

    # Right column: Move down
    for i in range(top, bottom + 1):
        matrix[i][right] = value
        value += 1
    right -= 1

    # Bottom row: Move left
    for k in range(right, left - 1, -1):
        matrix[bottom][k] = value
        value += 1
    bottom -= 1

    # Left column: Move up
    for i in range(bottom, top - 1, -1):
        matrix[i][left] = value
        value += 1
    left += 1

for i in range(n):
    for j in range(n):
        print(matrix[i][j], end=" ")
    print()
