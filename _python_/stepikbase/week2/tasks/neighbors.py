list_ = [int(str_) for str_ in input().split()]

if len(list_) == 1:
    print(list_[0])
    exit(0)

list2 = []
i = 0
while i < len(list_):
    left = list_[i - 1]

    if i == len(list_) - 1:
        right = list_[0]
    else:
        right = list_[i + 1]

    list2.append(left + right)
    i += 1

for i in list2:
    print(i, end=" ")
