class NestedIterator:
    def __init__(self, iterable, funcs, judge):
        self.i = 0
        self.length = len(iterable)
        self.iterable = iterable
        self.funcs = funcs
        self.judge = judge

    def __iter__(self):
        return self

    def __next__(self):
        while self.i < self.length:
            pos = 0
            neg = 0
            item = self.iterable[self.i]
            self.i += 1

            for func in self.funcs:
                if func(item):
                    pos += 1
                else:
                    neg += 1

            if self.judge(pos, neg):
                return item
            else:
                continue

        raise StopIteration


class multifilter:

    @staticmethod
    def judge_half(pos: int, neg: int) -> bool:
        # допускает элемент, если его допускает хотя бы половина функций (pos >= neg)
        return pos >= neg

    @staticmethod
    def judge_any(pos: int, neg: int) -> bool:
        # допускает элемент, если его допускает хотя бы одна функция (pos >= 1)
        return pos >= 1

    @staticmethod
    def judge_all(pos: int, neg: int) -> bool:
        # допускает элемент, если его допускают все функции (neg == 0)
        return neg == 0

    def __init__(self, iterable, *funcs, judge=judge_any):
        # iterable - исходная последовательность
        # funcs - допускающие функции
        # judge - решающая функция
        self.iterable = iterable
        self.funcs = funcs
        self.judge = judge

    def __iter__(self):
        # возвращает итератор по результирующей последовательности
        return NestedIterator(self.iterable, self.funcs, self.judge)


def mul2(x):
    return x % 2 == 0


def mul3(x):
    return x % 3 == 0


def mul5(x):
    return x % 5 == 0


a = [i for i in range(31)]  # [0, 1, 2, ... , 30]

filtered = multifilter(a, mul2, mul3, mul5)
print(list(filtered))
print(list(filtered))
# [0, 2, 3, 4, 5, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30]

print(list(multifilter(a, mul2, mul3, mul5, judge=multifilter.judge_half)))
# [0, 6, 10, 12, 15, 18, 20, 24, 30]

print(list(multifilter(a, mul2, mul3, mul5, judge=multifilter.judge_all)))
# [0, 30]
