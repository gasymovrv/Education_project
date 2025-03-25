from numpy import random as numpy_random  # подключаем явно иначе берется какой-то другой random
from pylab import figure, plot, xlabel, ylabel, title, show, linspace, plt

# example 1
x = [1, 2, 3, 4, 5]
y = [2, 3, 4, 5, 6]
print("x=", x)
print("y=", y)

figure()
plot(x, y, "r")
xlabel("x")
ylabel("y")
title("title")
show()

# example 2: Построение нескольких графиков в одном окне
x = linspace(0, 5, 10)  # функция из numpy, подтягивается через matplotlib
y = x ** 2
print("x=", x)
print("y=", y)

fig, ax = plt.subplots()
ax.plot(x, y, label="y = x**2")
ax.plot(x, x ** 3, label="y = x**3")
ax.legend(loc=2)  # upper left corner
ax.set_xlabel("x")
ax.set_ylabel("y")
ax.set_title("title")
plt.show()

# Example 3: Построение гистограмм
n = numpy_random.randn(100000)  # функция из numpy, создает массив из 100000 случайных чисел
fig, axes = plt.subplots(1, 2, figsize=(12, 4))

axes[0].hist(n)
axes[0].set_title("Default histogram")
axes[0].set_xlim((min(n), max(n)))

axes[1].hist(n, cumulative=True, bins=50)
axes[1].set_title("Cumulative detailed histogram")
axes[1].set_xlim((min(n), max(n)))
show()
