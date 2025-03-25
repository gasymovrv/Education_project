x = [1, 2, 3]

print("id(x)=", id(x))
print("id(x[0])=", id(x[0]))
print("id([1, 2, 3])=", id([1, 2, 3]))
y = x
print("id(y[0])=", id(y[0]))

print("x is y =", x is y)
print("x is [1, 2, 3]", x is [1, 2, 3])

a = 56735683583569
b = 56735683583569
print("a is b (int) =", a is b)  # True - кэш чисел как в джаве, но больше байта для литералов

a = "99930000025"
b = "99930000025"
print("a is b (str) =", a is b)  # True - как в джаве с пулом строк, но чуть сложнее (см. ниже)

a = 99930000025.0
b = 99930000025.0
print("a is b (float) =", a is b)  # True

# Кэширование строк зависит от длины и типа символов и может менять в разных интерпретаторах
# a = input("Введи строку a: ")
# b = input("Введи строку b: ")
# print("a is b (str from input) =", a is b)

# a = int(input("Введи число a: "))
# b = int(input("Введи число b: "))
# print("a is b (int from input) =", a is b)  # из байтового кэша, все что больше 256 - новые объекты

a = ["99930000025"]
b = ["99930000025"]
print("a is b (list) =", a is b)  # False
print("a[0] is b[0] =", a[0] is b[0])  # True

c = tuple([1, 2, 3])
d = (1, 2, 3)

print("c =", c)
print("d =", d)
print("c is d =", c is d)
print("c[0] =", c[0])
