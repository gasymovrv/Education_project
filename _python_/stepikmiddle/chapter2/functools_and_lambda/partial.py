import operator as op
from functools import partial

x = int("1101", base=2)
print(x)

int_2 = partial(int, base=2)  # Partially provided arguments
x = int_2("1101")
print(x)

x = [
    ("Guido", "van", "Rossum"),
    ("Haskell", "Curry"),
    ("John", "Backus")
]
sort_by_last = partial(list.sort, key=op.itemgetter(-1))
print(x)

sort_by_last(x)
print(x)

y = ["abc", "cba", "abb"]
print(y)
sort_by_last(y)
print(y)
