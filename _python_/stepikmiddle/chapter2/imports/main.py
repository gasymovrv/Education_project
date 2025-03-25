import sys

import lib
import pack1
from lib import *
from pack1 import *
from pack1 import f1
from pack1.f2 import *

print("lib =", lib)
print("id(lib) =", id(lib))
print("type(lib) =", type(lib))
print("lib.fib(6) =", fib(6))
print("lib._a =", lib._a)  # defined as private but still accessible because Python does not have true access control
print("abc =", abc)
print("sys.modules =", sys.modules)

# f3_func() # does not find it in lib.py because it is not in __all__, need to explicitly export
# _f4_func() # does not find it in pack1/__init__.py because it starts with underscore, need to explicitly export

print("\nsys.path:")
for path in sys.path:
    print(path)

print(pack1)
f1.f1_func()
f2_func()
init_fuc()
