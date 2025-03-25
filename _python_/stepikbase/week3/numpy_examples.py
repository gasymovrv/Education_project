from numpy import *

a = array([2, 3, 4])

print(a)
print(a.ndim)  # размерность массива (одномерный, двумерный итд)
print(a.shape)  # размеры массива (число строк, столбцов итд)

b = array([(1.5, 2, 3), (4, 5, 6)])

print(b)
print(b.ndim)
print(b.shape)

z = zeros((3, 2))  # 3, 2 помещаются в дополнительные скобки, чтобы представлять из себя один объект - пару чисел
print(z)

print(arange(1, 30, 5))  # функция arange аналогична функции range, но возвращает массив
print(linspace(0, 200, 15))  # генерирует 15 чисел на отрезке от 0 до 200 с равным шагом

b = arange(12).reshape(4, 3)
print(b)
print(b.ndim)
print(b.shape)

print("Арифметические операции на массивах выполняются поэлементно:")
print(a + b)
print(a - b)

print(a ** 2)
print(2 * sin(a))
print(a > 2)
