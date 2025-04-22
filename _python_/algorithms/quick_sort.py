def quick_sort(arr):
    """
    Quick sort.
    It's a divide and conquer technique (recursive algorithm that splits the array into two parts).
    """
    if not arr or len(arr) < 2:
        return arr

    pivot_idx = len(arr) // 2
    pivot = arr[pivot_idx]

    less = [num for i, num in enumerate(arr) if num <= pivot and i != pivot_idx]

    greater = [num for num in arr if num > pivot]

    return quick_sort(less) + [pivot] + quick_sort(greater)


print(quick_sort([3, 8, 3, 2, 0, 4, 1]))
print(quick_sort([3]))
print(quick_sort([9, 8]))
print(quick_sort([]))
