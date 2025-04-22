s = set()  # создание пустого множества
basket = {"apple", "orange", "apple", "pear", "orange", "banana"}
print(basket)
print("orange" in basket)  # True
print("python" in basket)  # False

element = "apple"
s = set(basket)  # Копирование множества basket. можно также basket.copy()
s.add(element)
print("after add:", s)
s.remove(element)  # выбрасывает исключение, если элемента нет
print("after remove:", s)
s.discard(element)  # не выбрасывает исключение, если элемента нет
print("after discard:", s)
s.clear()
print("cleared s:", s)
print("basket:", basket)

s1 = {1, 2, 3}
s2 = {2, 3, 4}
print("s1 | s2:", s1 | s2)  # объединение
print("s1 & s2:", s1 & s2)  # пересечение
print("s1 - s2:", s1 - s2)  # разность

s1 = {1, 2, 3}
s2 = {2, 3, 4}
print("s1 ^ s2:", s1 ^ s2)  # симметрическая разность - объединение без пересечения

s1 = {2, 3}
s2 = {2, 3, 4}
print("s1 < s2:", s1 < s2)  # If s1 is a proper subset of s2 - True
print("s1 > s2:", s1 > s2)  # False
print("s1 == s2:", s1 == s2)  # False
