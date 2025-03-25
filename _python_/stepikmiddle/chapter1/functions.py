# printab(1, 2)  # unresolved reference

def printab(a, b):
    print(a)
    print(b)


print("id(printab)=", id(printab))
print("id(printab)=", id(printab))

# Correct ways to call the function
printab(1, 2)
printab(b=2, a=1)

# Keyword arguments always after positional arguments
printab(1, b=2)

lst = [1, 2]
printab(*lst)  # unpacking, equivalent to printab(lst[0], lst[1])

dct = {"a": 1, "b": 2}
printab(**dct)  # unpacking, equivalent to printab(args["a"], args["b"])


# It replaces the previous definition
def printab(c, d=10):
    print(c)
    print(d)


print("id(printab)=", id(printab))

printab(5)  # c=5, d=10
printab(5, 15)  # c=5 d=15


def printab(a, b, *args):
    print(a)
    print(b)
    print(args, type(args))


printab(1, 2, 3, "4", 5)  # args will be tuple


def printab(a, b, **kwargs):
    print(a)
    print(b)
    print(kwargs, type(kwargs))


printab(1, 2, c=3, d="4", e=5)  # kwargs will be dict
printab(1, c=3, d="4", e=5, b=2)  # b as named argument after dict


# b can be provided only as named argument
def s(a, *vs, b=10):
    res = a + b
    print(vs, type(vs))
    for v in vs:
        res += v
    return res


print(s(21))
print(s(11, b=20))
