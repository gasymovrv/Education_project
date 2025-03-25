from time import sleep


def f(xi):
    sleep(2.5)
    return xi * 2


buffer = {}
n = int(input())

for _ in range(n):
    x = int(input())

    if x in buffer:
        print(buffer[x])
    else:
        f_result = f(x)
        buffer[x] = f_result
        print(f_result)
