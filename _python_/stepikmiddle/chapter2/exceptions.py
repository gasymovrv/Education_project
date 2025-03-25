try:
    x = [1, 2, "hello", 7]
    x.sort()
    print(x)
except TypeError:
    print("Can't sort list with non-numeric elements")

print("going on...")

print("=========f()=========")


def f(x, y):
    try:
        return x / y
    except TypeError:
        print("Type error")
    except ZeroDivisionError:
        print("division by zero!")


f(4, 0)

print("=========f2()=========")


def f2(x, y):
    try:
        return x / y
    except (TypeError, ZeroDivisionError) as e:
        print("error:", e)
        print("error type:", type(e))
        print("error args:", e.args)


f2(4, 0)
f2(4, "0")

print("=========f3()=========")


def f3(x, y):
    try:
        return x / y
    except:
        print("Unknown error")


def f3_1(x, y):
    try:
        return x / y
    except Exception as e:
        print("Unknown error: ", e)


f3(4, "0")
f3_1(4, "0")

print("=========f4()=========")


def f4(x, y):
    try:
        result = x / y
    except ZeroDivisionError as e:
        print("Error:", e)
    else:
        print("no exceptions, result is", result)
    finally:
        print("finally")


f4(4, 3)
f4(4, 0)

print(ZeroDivisionError.mro())
print(AssertionError.mro())


print("=========greet()=========")


class BadName(Exception):
    pass


def greet(name):
    if name == "Error":
        raise BadName("Error")
    elif name[0].isupper():
        return "Hello, " + name
    else:
        raise BadName("Name must start with a capital letter")


while True:
    try:
        name = input("Enter your name: ")
        print(greet(name))
        break
    except BadName as e:
        if e.args[0] == "Error":
            raise
        print("Please try again:", e)
