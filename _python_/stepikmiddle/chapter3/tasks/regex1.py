import re
import sys

result = []
pattern = re.compile("cat")

for line in sys.stdin:
    line = line.rstrip()
    if not line:
        break

    if len(re.findall(pattern, line)) >= 2:
        result.append(line)

print(*result, sep="\n")
