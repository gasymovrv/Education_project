def recursive_sum(arr, i=0, sum=0):
    if i == len(arr):
        return sum
    return recursive_sum(arr, i + 1, sum + arr[i])


def recursive_sum2(arr):
    if len(arr) == 0:
        return 0
    if len(arr) == 1:
        return arr.pop()

    return arr.pop() + recursive_sum2(arr)


def recursive_count(arr):
    if not arr:
        return 0
    return 1 + arr[1:]


print(recursive_sum([1, 2, 3, 4]))
print(recursive_sum2([1, 2, 3, 4]))
