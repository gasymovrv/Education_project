def mod_checker(n, mod=0):
    """Returns a function that checks if y % n == mod"""
    return lambda y: y % n == mod


mod_3 = mod_checker(3)

print(mod_3(3))  # True
print(mod_3(4))  # False

mod_3_1 = mod_checker(3, 1)
print(mod_3_1(4))  # True
