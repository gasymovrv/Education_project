import re
import sys

result = []
pattern = re.compile(r"^(0|(1(01*0)*1))*$")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    if re.match(pattern, line):
        result.append(line)

print(*result, sep="\n")

# Вам дана последовательность строк.
# Выведите строки, содержащие двоичную запись числа, кратного 3.

# Sample Input:
# 0
# 10010
# 00101
# 01001
# Not a number
# 1 1
# 0 0

# Sample Output:
# 0
# 10010
# 01001
