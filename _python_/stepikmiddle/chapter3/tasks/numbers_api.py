import sys

import requests

res = []
for n in sys.stdin:
    n = n.strip()
    if not n.isdigit():
        break

    resp = requests.get(f"http://numbersapi.com/{n}/math?json")
    if resp and resp.status_code // 200 == 1:
        resp = resp.json()
        print(resp)
    else:
        res.append(f"Error: {resp}")
        continue

    res.append("Interesting" if resp["found"] else "Boring")

for i in res:
    print(i)

# Пример входных данных:
# 31
# 999
# 1024
# 502

# Пример выходных данных:
# Interesting
# Boring
# Interesting
# Boring
