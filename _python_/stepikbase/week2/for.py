# Create generator and destruct it
generator = (int(i) for i in input().split(sep=","))
a, b, c, d = generator

print("\t", end="")
for i in range(c, d + 1):
    print(i, end="\t")
print()

# From the end to the beginning (second arg is stop index exclusive, last -1 is step)
lst = [1, 2, 3, 4, 5]
for i in range(len(lst) - 1, -1, -1):
    print(lst[i])

for j in range(1, 1):
    print("Never printed")
for j in range(1, 0):
    print("Never printed")
