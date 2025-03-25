t_c = 18  # global variable
tmp = "ok"  # global variable


def fahrenheit(t_c):  # local variable override global
    tmp = t_c * 9 / 5  # local variable override global
    return tmp + 32


# def wrong(t_c):
#     wrong = tmp + 32  # can"t use global variable if local one defined lower
#     tmp = t_c * 9 / 5


print(fahrenheit(t_c))
print(tmp)


def a():
    a_scope_var = 10
    b()


def b():
    print(global_var)
    # print(a_scope_var)  # Unresolved reference "a_scope_var"


# a()  # NameError: name "global_var" is not defined
global_var = 5
a()


def a():
    a_scope_var = 10

    def b():
        print(global_var)
        print(a_scope_var)  # fine

    b()


a()

for i in range(5):
    y = i * i  # global variable, python doesn"t have block scope
print("global y defined in for:", y)

for i in []:
    x = i * i  # global variable which is not defined
# print(x)  # NameError: name "x" is not defined


def scope_test():
    spam = "test spam"  # enclosing scope

    def do_local():
        spam = "local spam"

    def do_nonlocal():
        nonlocal spam
        spam = "nonlocal spam"  # can change vars from enclosing scope

    def do_global():
        global spam
        spam = "global spam"  # can change vars from global scope

    do_local()
    print("scope_test().spam after local assignment:", spam)
    do_nonlocal()
    print("scope_test().spam after nonlocal assignment:", spam)
    do_global()
    print("scope_test().spam after global assignment:", spam)


spam = ""
scope_test()
print("In global scope spam = ", spam)
