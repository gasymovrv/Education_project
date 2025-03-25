a = int(input())
b = int(input())
c = int(input())
d = int(input())

if a > 10 or b > 10 or c > 10 or d > 10:
    print("Error: numbers must be less than or equal 10")
    exit(1)

if a > b:
    print("Error: a must be less than or equal b")
    exit(1)

if c > d:
    print("Error: c must be less than or equal d")
    exit(1)


print("\t", end="")
for i in range(c, d + 1):
    print(i, end="\t")
print()

for i in range(a, b + 1):
    print(i, end="\t")
    for j in range(c, d + 1):
        print(i * j, end="\t")
    print()
