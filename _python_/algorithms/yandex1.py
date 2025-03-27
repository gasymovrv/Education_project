from collections import Counter


def count_pairs(arr: list[int], k: int) -> int:
    """
    Дан массив целых чисел a_1, a_2, ..., a_n и неотрицательное число k.
    Найдите количество пар элементов массива (i, j), таких, что i < j и |a_i - a_j| = k (модуль разности равен k).
    n < 10^5
    -10^9 < a < 10^9
    :return: количество пар
    """
    count = 0
    counter_map = Counter()

    for num in arr:
        if num - k in counter_map:
            count += counter_map[num - k]

        # Если не исключить k == 0, то (num - k) == (num + k) и мы посчитаем пару дважды
        if k != 0 and num + k in counter_map:
            count += counter_map[num + k]

        counter_map[num] += 1

    return count

print(count_pairs([1, 5, 3, 4, 2], 2))  # 3
print(count_pairs([1, 5, 3, 4, 2], 0))  # 0
print(count_pairs([1, 1], 0))  # 1
