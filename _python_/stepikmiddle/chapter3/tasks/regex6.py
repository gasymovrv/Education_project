import sys

result = []

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    result.append(line.replace("human", "computer"))

print(*result, sep="\n")

# Sample Input:
# I need to understand the human mind
# humanity

# Sample Output:
# I need to understand the computer mind
# computerity
