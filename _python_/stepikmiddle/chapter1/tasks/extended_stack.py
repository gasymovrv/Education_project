class ExtendedStack(list):
    def sum(self):
        # операция сложения
        first = super().pop()
        second = super().pop()
        super().append(first + second)

    def sub(self):
        # операция вычитания
        first = super().pop()
        second = super().pop()
        super().append(first - second)

    def mul(self):
        # операция умножения
        first = super().pop()
        second = super().pop()
        super().append(first * second)

    def div(self):
        # операция целочисленного деления
        first = super().pop()
        second = super().pop()
        super().append(first // second)


stack = ExtendedStack([1, 2, 3, 4])

stack.div()
print(stack)
