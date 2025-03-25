ticket = int(input())
first = ticket // 1000
second = ticket % 1000

l1 = first // 100
l2 = (first // 10) % 10
l3 = first % 10

l4 = second // 100
l5 = (second // 10) % 10
l6 = second % 10


if (l1 + l2 + l3) == (l4 + l5 + l6):
    print("Счастливый")
else:
    print("Обычный")
