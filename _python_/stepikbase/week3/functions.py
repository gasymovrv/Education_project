def min2(a, b):
    if a < b:
        return a
    else:
        return b


def f(n):
    return n * 10 + 5


def my_range(start, stop, step=1):
    res = []
    if step > 0:
        x = start
        while x < stop:
            res += [x]
            x += step
    elif step < 0:
        x = start
        while x > stop:
            res += [x]
            x += step
    return res


def append_zero(xs):
    xs.append(0)


def print_value():
    print(a)  # use global variable


# can"t use global variable if local one defined
# def print_value2():
#     print(a)
#     a = 10


print(my_range(1, 10, 2))
print(my_range(10, 1, -2))
print(min2(2, 3))
print(f(f(f(10))))

arr = []
append_zero(arr)
append_zero(arr)
append_zero(arr)
append_zero(arr)
print(arr)

a = -10
print_value()  # use global variable


def modify_list(list_):
    i = 0
    while i < len(list_):
        if list_[i] % 2 == 0:
            list_[i] = list_[i] // 2
        else:
            del list_[i]
            i -= 1
        i += 1


lst = [1, 2, 3, 4, 5, 6]
print(modify_list(lst))  # None
print(lst)  # [1, 2, 3]
modify_list(lst)
print(lst)  # [1]

lst = [10, 5, 8, 3]
modify_list(lst)
print(lst)


# Default arguments created only once on function definition and can be mutable by func calls!
def f1(lst=[]):
    print(f"lst id: {id(lst)}")
    lst.append(1)
    print(lst)


f1()  # lst id: 139874235124288 | [1]
f1()  # lst id: 139874235124288 | [1, 1]
f1()  # lst id: 139874235124288 | [1, 1, 1]
