from random import random


class RandomIterator:
    """
    RandomIterator(k) returns an iterator that returns k random numbers in the range [0.0, 1.0).

    Uses random.random()
    """

    def __iter__(self):
        return self

    def __init__(self, k):
        self.k = k
        self.i = 0

    def __next__(self):
        if self.i < self.k:
            self.i += 1
            return random()
        else:
            raise StopIteration

for x in RandomIterator(10):
    print(x)
