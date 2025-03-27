def factorial(n):
    res = 1
    for i in range(2, n + 1):
        res = res * i
    return res


def factorial2(n):
    if n == 1:
        return 1
    return n * factorial2(n - 1)


print(factorial(5))
