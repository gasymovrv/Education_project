import time

list_ = [int(str_) for str_ in input().split()]
# list_ = [i for i in range(100000)] * 2
list_.sort()
duplicates = []
lastEl = None

start_time = time.time()

# var 1
for i, el in enumerate(list_):
    if lastEl != el and i != len(list_) - 1 and el == list_[i + 1]:
        duplicates.append(el)
        lastEl = el

# var 2 Very slow because of count()
# for el in list_:
#     if lastEl != el and list_.count(el) > 1:
#         duplicates.append(el)
#         lastEl = el

print("--- %s seconds ---" % (time.time() - start_time))

for i in duplicates:
    print(i, end=" ")
