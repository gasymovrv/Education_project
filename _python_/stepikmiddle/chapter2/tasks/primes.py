import itertools


def primes():
    n = 2
    yield n

    while True:
        n += 1
        dividers = 0
        for i in range(2, n + 1):
            if n % i == 0:
                dividers += 1

        if dividers == 1:
            yield n


print(list(itertools.takewhile(lambda x: x <= 31, primes())))
# [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]

# Строка выше тоже самое что и:
gen = primes()
i = 0
while i <= 31:
    i = next(gen)
    print(i, end=", ", )
