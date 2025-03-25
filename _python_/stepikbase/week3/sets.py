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
