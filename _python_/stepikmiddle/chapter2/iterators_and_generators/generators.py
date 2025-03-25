from random import random


def simple_gen():
    print("Checkpoint 1")
    yield 1
    print("Checkpoint 2")
    yield 2
    print("Checkpoint 3")

gen = simple_gen()
x = next(gen)
print(x)
y = next(gen)
print(y)
# z = next(gen)  # StopIteration



print("=============================================")

def simple_gen_with_return(stop: bool):
    print("Checkpoint 1")
    yield 1
    print("Checkpoint 2")
    if stop:
        yield 2
        # return causes throwing StopIteration same as calling next() when there is no more yields in function
        return "This will be the description of StopIteration error"
    print("Checkpoint 3")
    yield 3

gen = simple_gen_with_return(True)
x = next(gen)
print(x)
y = next(gen)
print(y)
# z = next(gen)  # StopIteration: This will be the description of StopIteration error



print("=============================================")

def random_generator(k):
    for i in range(k):
        yield random()


gen = random_generator(3)

for i in gen:
    print(i)
