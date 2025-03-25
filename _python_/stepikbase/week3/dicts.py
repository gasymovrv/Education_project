d = dict()
d = {}
d = {"a": 239, 10: 100}
print("d=", d)
print("d[\"a\"]=", d["a"])
print("d[10]=", d[10])

dictionary = dict(d)  # copy
key = "a"
print(key in dictionary)
print(key not in dictionary)

dictionary[key] = 42
print(dictionary[key])

del dictionary[key]
print(dictionary)

d = {"C": 14, "A": 12, "T": 9, "G": 18}
for key in d:
    print(key, end=" ")
print()

for key in d.keys():
    print("key=", key, end=", ")
print()

for value in d.values():
    print("value=", value, end=", ")
print()

for key, value in d.items():
    print(key, value, sep=":")


def update_dictionary(d, key, value):
    if key in d:
        d[key].append(value)
    elif 2 * key in d:
        d[2 * key].append(value)
    else:
        d[2 * key] = [value]


d = {}
print(update_dictionary(d, 1, -1))  # None
print(d)  # {2: [-1]}
update_dictionary(d, 2, -2)
print(d)  # {2: [-1, -2]}
update_dictionary(d, 1, -3)
print(d)  # {2: [-1, -2, -3]}


my_dict = {'name': 'Alice', 'age': 25}

# Using []
print(my_dict['name'])  # ✅ Alice
# print(my_dict['gender'])  # ❌ KeyError!

# Using get()
print(my_dict.get('name'))    # ✅ Alice
print(my_dict.get('gender'))  # None
print(my_dict.get('gender', 'Unknown'))  # ✅ Custom Default Value: Unknown
