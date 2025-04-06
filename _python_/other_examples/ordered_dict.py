from collections import OrderedDict

# Creating an OrderedDict
ordered_dict = OrderedDict()

# Adding key-value pairs
ordered_dict[1] = "A"
ordered_dict[2] = "B"
ordered_dict[3] = "C"

# Accessing an existing key (does not move it to the end)
print(ordered_dict[1])  # "A"
print(ordered_dict)  # OrderedDict([(1, 'A'), (2, 'B'), (3, 'C')])

# Adding a new key (this moves it to the end)
ordered_dict[4] = "D"
print(ordered_dict)  # OrderedDict([(1, 'A'), (2, 'B'), (3, 'C'), (4, 'D')])

# Updating an existing key (does not move it to the end)
ordered_dict[1] = "Updated A"
print(ordered_dict)  # OrderedDict([(1, 'Updated A'), (2, 'B'), (3, 'C'), (4, 'D')])


ordered_dict = OrderedDict([(1, 'A'), (2, 'B'), (3, 'C'), (4, 'D')])

# Show the original OrderedDict
print("Original OrderedDict:", ordered_dict)

# --- popitem(last=True) example (removes most recently added item) ---
item = ordered_dict.popitem(last=True)
print("\nPopped item (last=True):", item)
print("OrderedDict after popitem(last=True):", ordered_dict)

# --- popitem(last=False) example (removes least recently added item) ---
item = ordered_dict.popitem(last=False)
print("\nPopped item (last=False):", item)
print("OrderedDict after popitem(last=False):", ordered_dict)

# --- move_to_end(key) example (move a key to the end) ---
ordered_dict.move_to_end(2)
print("\nOrderedDict after move_to_end(2):", ordered_dict)

# --- move_to_end(key, last=False) example (move a key to the front) ---
ordered_dict.move_to_end(3, last=False)
print("\nOrderedDict after move_to_end(3, last=False):", ordered_dict)

# --- Adding new items to see the effect of popitem(last=True) ---
ordered_dict[5] = 'E'
ordered_dict[6] = 'F'
print("\nOrderedDict after adding more items:", ordered_dict)

# --- popitem(last=True) to remove the most recently added item ---
item = ordered_dict.popitem(last=True)
print("\nPopped item (last=True) after adding more items:", item)
print("OrderedDict after popitem(last=True) again:", ordered_dict)
