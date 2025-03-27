def recursive_max(arr, i=0, num=float("-inf")):
    if i == len(arr):
        return num

    if arr[i] > num:
        return recursive_max(arr, i + 1, arr[i])
    else:
        return recursive_max(arr, i + 1, num)


def recursive_max2(arr):
    if not arr:
        return float("-inf")
    if len(arr) == 1:
        return arr[0]

    el1 = arr.pop()
    el2 = recursive_max2(arr)
    if el1 > el2:
        return el1
    return el2


def recursive_max3(arr):
    if not arr:
        return float("-inf")
    if len(arr) == 1:
        return arr[0]

    el1 = arr[0]
    el2 = recursive_max2(arr[1:])
    if el1 > el2:
        return el1
    return el2


print(recursive_max([-4, 6, 8, -88, 3, 11, 6, 2, 0]))
print(recursive_max2([-4, 6, 8, -88, 3, 11, 6, 2, 0]))
print(recursive_max3([-4, 6, 8, -88, 3, 11, 6, 2, 0]))
print(recursive_max3([-4, 0]))
print(recursive_max3([]))
