year = int(input())

if (year % 4 == 0 and year % 100 != 0) or year % 400 == 0:
    print("Високосный")
else:
    print("Обычный")

# if в виде тернарного выражения:
print("Високосный" if (year % 4 == 0 and year % 100 != 0) or year % 400 == 0 else "Обычный")
