from functools import cmp_to_key

# x = input("Provide 2 numbers:\n").split()
# print(x)  # ["1", "2"]

# first argument of map is function (constructor of type int is also function int.__init__(str) -> int),
# second argument is iterable.
# n, k - unpacking of iterable object. if iterable is more or less than 2 elements, it throws error
# n, k = map(int, x)
# print(n + k)

# the same as `n, k = map(int, x)` but using generator instead
# n, k = (int(i) for i in x)
# print(n + k)

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
