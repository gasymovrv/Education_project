class MyClass:
    tags = []  # атрибут класса
    a = 10  # атрибут класса

    def func(self):  # self - ссылка на экземпляр класса
        print("Hello")

    def change_a(self, a):
        # self.a добавит новую переменную в экземпляр self, переменная "а" класса не изменится
        self.a = a

    def wrong_change_tags(self, a):
        # self.tags будет сначала искать в экземпляре, потом в классе.
        # т.к. в экземпляре нет переменной "tags", то в итоге мы меняем состояние переменной "tags" в классе
        self.tags.append(a)

    @staticmethod
    def change_static_a(a):
        MyClass.a = a

obj1 = MyClass()
print(type(obj1))
print(type(MyClass))
print(type(MyClass.func))
MyClass.func(obj1)
obj1.func()
print(MyClass.a)
print(obj1.a)
obj1.b = 222
print(obj1.b)

obj2 = MyClass()
obj1.change_a(99)
print("MyClass.a =", MyClass.a)  # 10
print("obj1.a =", obj1.a)  # 99
print("obj2.a =", obj2.a)  # 10

obj1.wrong_change_tags(100)
print("obj1.tags =", obj1.tags)  # [100]
print("obj2.tags =", obj2.tags)  # [100]

MyClass.change_static_a(33)
print("MyClass.a =", MyClass.a)  # 33


class Counter:
    def __init__(self):
        self.count = 0

    def __getitem__(self, item):
        """
         It uses the built-in getattr() function to retrieve the attribute.
        :param item: attribute name
        :return: attribute value
        """
        return getattr(self, item)

    def inc(self):
        self.count += 1

    def reset(self):
        self.count = 0


counter = Counter()
counter.inc()
print(type(counter.inc))  # <class "method">
print(counter.count)
Counter.inc(counter)  # It"s the same as counter.inc()
print(counter.count)
counter.reset()
print(counter.count)
print(counter["count"])
print(counter.__getitem__.__doc__)
