import time
from collections import Counter, defaultdict

lst = ["a", "b", "a", "c", "b", "a"]

print("\n===================== counting using dict =====================")
counter = {}
for item in lst:
    counter[item] = counter.get(item, 0) + 1
print(counter)

print("\n===================== counting using Counter =====================")
counter = Counter(lst)
counter["missing"]  # Does not raise KeyError and not add "missing" as defaultdict does
print(counter)
print("missing" in counter)  # False
counter["c"] = 0  # Set the count of 'c' to 0
print("c" in counter)  # True
for x in counter.items():
    print(x[0], x[1])
print([i for i in counter.elements()])  # ['a', 'a', 'a', 'b', 'b', 'c'] - repeated elements according to their count

print("\n===================== counting using defaultdict =====================")
counter = defaultdict(int)  # int used as factory to create default values (int() creates 0)
for item in lst:
    counter[item] += 1
print(min(counter.keys()))
print(dict(counter))

print("\n===================== compare defaultdict and dict =====================")
#  Instead of doing a second lookup (like in a regular dict),
#  it holds a slot for the missing key and immediately fills it with the default value in a single step.
default_dict = defaultdict(int)
# default_dict = defaultdict(lambda: -1)  # Example of other factory
default_dict["missing"]  # ✅ Automatically set to 0
print(default_dict)  # defaultdict(<class "int">, {"missing": 0})

regular_dict = {}
# regular_dict["missing"]  # ❌ KeyError!


print("\n===================== compare performance =====================")
lst = ["a", "b", "a", "c", "b", "a"] * 1000000

# Using dict.get()
start = time.time()
counter_dict = {}
for item in lst:
    counter_dict[item] = counter_dict.get(item, 0) + 1
end = time.time()
print("dict (million elements incremented):", end - start)

# Using Counter
start = time.time()
counter = Counter(lst)
end = time.time()
print("Counter (million elements incremented):", end - start)

# Using defaultdict
start = time.time()
counter_dd = defaultdict(int)
for item in lst:
    counter_dd[item] += 1
end = time.time()
print("defaultdict (million elements incremented):", end - start)
