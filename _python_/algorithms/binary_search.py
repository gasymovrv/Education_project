def binary_search(arr, n):
    left = 0
    right = len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if n < arr[mid]:
            right = mid - 1
        elif arr[mid] < n:
            left = mid + 1
        else:
            return mid
    return -1


def binary_search_recursive(arr, n, left=0, right=None):
    """Рекурсивный бинарный поиск. Возвращает индекс элемента n в массиве arr или -1, если элемент не найден."""
    if right is None:
        right = len(arr) - 1  # Устанавливаем предел при первом вызове

    if left > right:
        return -1  # Элемент не найден

    mid = (left + right) // 2  # Находим середину

    if arr[mid] == n:
        return mid  # Найден элемент, возвращаем индекс
    elif arr[mid] < n:
        return binary_search_recursive(arr, n, mid + 1, right)  # Поиск в правой половине
    else:
        return binary_search_recursive(arr, n, left, mid - 1)  # Поиск в левой половине


arr = [1, 3, 5, 7, 9, 11]

print(binary_search(arr, 5))  # 2
print(binary_search(arr, 8))  # -1
print(binary_search(arr, 1))  # 0
print(binary_search(arr, 11))  # 5
print(binary_search(arr, 0))  # -1

print()

print(binary_search_recursive(arr, 5))  # 2
print(binary_search_recursive(arr, 8))  # -1
print(binary_search_recursive(arr, 1))  # 0
print(binary_search_recursive(arr, 11))  # 5
print(binary_search_recursive(arr, 0))  # -1
