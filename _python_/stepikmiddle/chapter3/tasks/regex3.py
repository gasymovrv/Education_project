import re
import sys

result = []
pattern = re.compile(r"z.{3}z")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    if re.search(pattern, line):
        result.append(line)

print(*result, sep="\n")
