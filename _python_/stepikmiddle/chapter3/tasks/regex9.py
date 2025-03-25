import re
import sys

result = []
pattern = re.compile(r"(\w)\1+")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    result.append(re.sub(pattern, r"\1", line))

print(*result, sep="\n")

# Вам дана последовательность строк.
# В каждой строке замените все вхождения нескольких одинаковых букв на одну букву.
# Буквой считается символ из группы \w.

# Sample Input:
# attraction
# buzzzz

# Sample Output:
# atraction
# buz
