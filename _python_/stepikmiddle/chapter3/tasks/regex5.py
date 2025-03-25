import re
import sys

result = []
pattern = re.compile(r"\b(\w+)\1\b")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    search = re.search(pattern, line)
    print(search)
    if search:
        result.append(line)

print(*result, sep="\n")

# Вам дана последовательность строк.
# Выведите строки, содержащие слово, состоящее из двух одинаковых частей (тандемный повтор).

# Sample Input:
# blabla is a tandem repetition
# 123123 is good too
# go go
# aaa
# word and wordword is a tandem repetition, andand is also a tandem repetition

# Sample Output:
# blabla is a tandem repetition
# 123123 is good too
