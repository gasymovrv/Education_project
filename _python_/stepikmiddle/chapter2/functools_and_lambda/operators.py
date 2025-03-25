import operator as op

print(op.add(4, 5))
print(op.mul(4, 5))
print(op.contains([1, 2, 3], 4))  # 4 in [1, 2, 3]

lst = [1, 2, 3]
f = op.itemgetter(0)  # f(lst) == lst[0]
print(f(lst))

x = {"123": 3}
f = op.itemgetter("123")  # f(x) == x["123"]
print(f(x))

f = op.attrgetter("sort")  # f(lst) == lst.sort
print(f(lst))  # <built-in method sort of list object at 0x000002140C0D6440>
f(lst)(reverse=True)
print(lst)

x = [
    ("Guido", "van", "Rossum"),
    ("Haskell", "Curry"),
    ("John", "Backus")
]

x.sort(key=op.itemgetter(-1))  # sort by last name (x[i][-1])
print(x)
