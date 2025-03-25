import re
import sys

result = []
pattern = re.compile(r"\ba+\b")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    result.append(re.sub(pattern, "argh", line, count=1, flags=re.IGNORECASE))

print(*result, sep="\n")

# Вам дана последовательность строк.
# В каждой строке замените первое вхождение слова, состоящего только из латинских букв "a" (регистр не важен), на слово "argh".

# Sample Input:
# There’ll be no more "Aaaaaaaaaaaaaaa"
# AaAaAaA AaAaAaA

# Sample Output:
# There’ll be no more "argh"
# argh AaAaAaA
