import re
import sys

result = []
pattern = re.compile(r"\b(\w)(\w)(\w*)\b")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    result.append(re.sub(pattern, r"\2\1\3", line))

print(*result, sep="\n")

# Вам дана последовательность строк.
# В каждой строке поменяйте местами две первых буквы в каждом слове, состоящем хотя бы из двух букв.
# Буквой считается символ из группы \w.

# Sample Input:
# this is a text
# "this' !is. ?n1ce,

# Sample Output:
# htis si a etxt
# "htis' !si. ?1nce,
