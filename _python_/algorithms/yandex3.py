def sum_of_target(arr: list[int], target: int) -> list[int]:
    """
    Input - массив целых чисел и целое число target. Нужно вернуть индекс начала и конца первого подмассива,
    сумма которого равна target. Пример: Input: nums = [9, -6, 5, 1, 4, -2], target = 10 Output: [2, 4] (5 + 1 + 4 =
    10) :return: индекс начала и конца первого подмассива
    """
    if not arr:
        return [-1, -1]

    prefix_sums = {0: -1}
    sum_ = 0

    for i in range(len(arr)):
        sum_ += arr[i]
        if sum_ - target in prefix_sums:
            return [prefix_sums[sum_ - target] + 1, i]

        prefix_sums[sum_] = i

    return [-1, -1]


print(sum_of_target([9, -6, 5, 1, 4, -2], 10))  # [2, 4]
print(sum_of_target([9, -6, 5, 1, 4, -2], 2))  # [4, 5]
print(sum_of_target([9, -6, 5, 1, 4, -2], 5))  # [2, 2]
