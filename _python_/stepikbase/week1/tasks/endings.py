n = int(input())

mod10 = n % 10
mod100 = (n % 100) // 10

if mod10 == 1 and mod100 != 1:
    print(n, "программист")
elif mod10 == 2 and mod100 != 1:
    print(n, "программиста")
elif mod10 == 3 and mod100 != 1:
    print(n, "программиста")
elif mod10 == 4 and mod100 != 1:
    print(n, "программиста")
else:
    print(n, "программистов")
