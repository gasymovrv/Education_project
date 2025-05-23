import random
from functools import cmp_to_key, lru_cache

# x = input("Provide 2 numbers:\n").split()
x = ["1", "2"]
print(x)  # ["1", "2"]

# first argument of map is function (constructor of type int is also function int.__init__(str) -> int),
# second argument is iterable.
# n, k - unpacking of iterable object. if iterable is more or less than 2 elements, it throws error
n, k = map(int, x)
print(n + k)

x2 = [1, 2, 3, 4, 5]
print(min(map(lambda item: abs(item - 1), x2)))

# the same as `n, k = map(int, x)` but using generator instead
n, k = (int(i) for i in x)
print(n + k)

# xs = (int(i) for i in input("Provide numbers for filter by even:\n").split())
# iterator = filter(lambda num: num % 2 == 0, xs)
# print(list(iterator))  # converts iterator (that was returned by filter) to list

x = [
    ("Guido", "van", "Rossum"),
    ("Haskell", "Curry"),
    ("John", "Backus")
]
x.sort(key=lambda tuple_: len(" ".join(tuple_)))
print(x)


def person_comparator(p1: dict, p2: dict):
    # Sort by birthday descending
    if p1["birthday"] > p2["birthday"]:
        return -1
    elif p1["birthday"] < p2["birthday"]:
        return 1

    # If birthdays are the same, sort by name ascending (boolean is converted to int (1 or 0))
    return (p1["name"] > p2["name"]) - (p1["name"] < p2["name"])  # Equivalent to cmp()


persons = [
    {"name": "Alice", "birthday": "1990-05-20"},
    {"name": "Bob", "birthday": "1985-04-15"},
    {"name": "Charlie", "birthday": "1990-05-20"},
    {"name": "Alex", "birthday": "1990-05-20"},
    {"name": "David", "birthday": "1992-07-10"},
    {"name": "Eve", "birthday": "1985-04-15"},
    {"name": "Evan", "birthday": "1985-04-15"}
]
persons.sort(key=cmp_to_key(person_comparator))
print(persons)


@lru_cache(maxsize=10)
def cached_random(n: int):
    print(n)
    return random.random()


print("\nCached random first invocation")
print(cached_random(1))
print(cached_random(2))

print("\nCached random second invocation")
print(cached_random(1))  # same result as first invocation and nested print is not executed
print(cached_random(2))  # same result as first invocation and nested print is not executed


# Without lru_cache, computing fib(50) recursively would take hours. With it, it takes milliseconds.
# It automatically caches the results of function calls based on their arguments,
# so repeated calls with the same inputs don't recompute the result—they just return the cached result.
@lru_cache(maxsize=None)
def fib(n):
    if n <= 1:
        return n
    return fib(n - 1) + fib(n - 2)


print("\nCached fibonacci")
print(fib(50))
