import datetime

year, month, day = [int(s) for s in input().split()]
days = int(input())

d = datetime.date(year, month, day)
d += datetime.timedelta(days=days)
print(d.year, d.month, d.day)
