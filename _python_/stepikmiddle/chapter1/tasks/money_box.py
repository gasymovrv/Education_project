class MoneyBox:
    def __init__(self, capacity):
        # конструктор с аргументом – вместимость копилки
        self.capacity = capacity
        self.savings = 0

    def can_add(self, v):
        # True, если можно добавить v монет, False иначе
        if self.savings + v > self.capacity:
            return False
        else:
            return True

    def add(self, v):
        # положить v монет в копилку
        if self.can_add(v):
            self.savings += v
        else:
            print("Не хватает места")


mb = MoneyBox(5)
mb.add(3)
print(mb.savings)
mb.add(1)
print(mb.savings)
mb.add(2)
print(mb.savings)
mb.add(1)
print(mb.savings)
