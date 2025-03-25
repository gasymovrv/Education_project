import sys

a = int()  # 0 by default
print(a)

print("\n======== Возведение в степень ** =========")
print(9 ** 2 - int(float(9 ** 2)))  # 0
print(9 ** 19 - int(float(9 ** 19)))  # 89 - Большие float теряют точность
print(9 ** 50)
print(2 ** 3)
print(int(9. ** 19))
print(type(1350851717672992089.))
print(1350851717672992189568.)
print("\n======== Целочисленное деление // =========")
print(205 // 200)
print("\n======== Дробное деление // =========")
print(205 / 200)
print("\n======== Остаток от деления % =========")
print(2 % 5)
print(7 % 5)

print("\n======== Константы плавающей точности =========")
print(float("NaN"))  # nan = Not a Number
print(float("NaN") + float("inf"))  # nan

inf = float("inf")  # Infinity
neg_inf = float("-inf")  # -Infinity
print(inf)
print(neg_inf)
print(neg_inf > 0)  # False
print(neg_inf < -12345346345645723752457)  # True
print(inf < 0)  # False
print(inf > 12345346345645723752457)  # True
print(float("inf") - float("inf"))  # nan
print(float("inf") + float("-inf"))  # nan
print(float("inf") + float("inf"))  # inf
print(float("inf") - float("-inf"))  # inf
print(float("-inf") + float("-inf"))  # -inf
print(float("-inf") - float("inf"))  # -inf

print("\n======== Другое =========")
a1 = 1
a1 = "235"
a1 = True

print(type(a1))

print("1 is = ", bool(1))
print("not 1 is = ", bool(not 1))
print("2.75 is = ", bool(2.75))
print("0 is = ", bool(0))
print("0.0 is = ", bool(0.0))
print("empty str is = ", bool(""))
print("str is = ", bool("str"))

a2 = 50
print("Classic comparisons: ", 10 <= a2 and a2 < 100)
print("Chain of comparisons: ", 10 <= a2 < 100)

print("\n======== Операции с boolean =========")
print(True - True)  # 0
print(True - False)  # 1
print(False - False)  # 0
print(False - True)  # -1
print(True + True)  # 2
print((True + True) * 2)  # 4
print(True * True)  # 1

print("\n======== Размер памяти =========")
print("sys.getsizeof(42) =", sys.getsizeof(42))  # Integer
print("sys.getsizeof(3.14) =", sys.getsizeof(3.14))  # Float
print("sys.getsizeof(True) =", sys.getsizeof(True))  # Boolean
print("sys.getsizeof('Hello') =", sys.getsizeof("Hello"))  # String
print(sys.getsizeof([]))  # Empty list
print(sys.getsizeof(()))  # Empty tuple
print(sys.getsizeof({}))  # Empty dict
print(sys.getsizeof(set()))  # Empty set

for i in range(5):
    y = i

print(y)
