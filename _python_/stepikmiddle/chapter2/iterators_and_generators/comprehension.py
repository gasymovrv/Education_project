x = [-2, -1, 0, 1, 2]
# List comprehension:
y = [i * i for i in x if i > 0]
print(y)

# Dictionary comprehension:
squares = {x: x * x for x in range(5)}
print(squares)  # Output: {0: 0, 1: 1, 2: 4, 3: 9, 4: 16}

# Set comprehension:
unique_values = {x % 3 for x in range(10)}
print(unique_values)  # Output: {0, 1, 2}

# List comprehension:
z = [(x, y) for x in range(3) for y in range(3) if y >= x]
print(z)

# It"s the same as:
z = []
for x in range(3):
    for y in range(3):
        if y >= x:
            z.append((x, y))
print(z)

# Generator expression:
gen = ((x, y) for x in range(3) for y in range(3) if y >= x)
print(gen)  # <generator object <genexpr> at ...>
print(next(gen))
print(next(gen))
print(next(gen))
