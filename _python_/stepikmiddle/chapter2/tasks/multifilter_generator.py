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

    def __generator(self):
        for i in self.iterable:
            pos = 0
            neg = 0
            for func in self.funcs:
                if func(i):
                    pos += 1
                else:
                    neg += 1

            if self.judge(pos, neg):
                yield i
            else:
                continue

    def __init__(self, iterable, *funcs, judge=judge_any):
        # iterable - исходная последовательность
        # funcs - допускающие функции
        # judge - решающая функция
        self.iterable = iterable
        self.funcs = funcs
        self.judge = judge

    def __iter__(self):
        # возвращает итератор по результирующей последовательности
        return self.__generator()


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
