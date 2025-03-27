def find_smallest(arr):
    smallest = arr[0]
    smallest_index = 0
    for i in range(1, len(arr)):
        if arr[i] < smallest:
            smallest = arr[i]
            smallest_index = i
    return smallest_index


def selection_sort(arr):
    result = []
    for i in range(len(arr)):
        smallest = find_smallest(arr)
        result.append(arr.pop(smallest))
    return result


print(selection_sort([4, 3, 1, 8, 2, 5, 7, 6]))
