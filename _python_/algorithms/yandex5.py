from collections import Counter


def count_matching_pairs(nums1, nums2):
    """
    Даны 2 массива целых чисел одинаковой длины.
    Проходясь одновременно по обоим массивам начиная с i == 0 нужно подсчитать, сколько чисел из этих массивов (nums1[i], nums2[i]) уже встретились на индексах < i.
    В результате вывести массив с элементами - количеством встреченных ранее чисел на каждом индексе

    Еще один вариант описания:
    Counts pairs of equal elements in two lists.

    Given two lists of integers, nums1 and nums2, of equal length n,
    count the number of pairs of equal elements at each index i,
    and return a list of length n with these counts.

    For example, if nums1 = [1, 2, 3, 1], nums2 = [2, 1, 3, 4],
    then the output will be [0, 1, 1, 1], since there are 0 pairs of equal
    elements at index 0, 1 pair at index 1 (since 2 equals 2), 1 pair at
    index 2 (since 3 equals 3), and 1 pair at index 3 (since 1 equals 1).

    :param nums1: list of integers
    :param nums2: list of integers
    :return: list of length n with counts of pairs of equal elements
    """

    n = len(nums1)
    res = [0] * n
    unpaired = Counter()  # Счётчик "ожидающих" чисел
    pair_count = 0

    for i in range(n):
        for num in (nums1[i], nums2[i]):
            if unpaired[num] > 0:
                unpaired[num] -= 1  # Пара найдена — уменьшаем счётчик
                pair_count += 1
            else:
                unpaired[num] += 1  # Ждём пару — увеличиваем счётчик
        res[i] = pair_count

    return res


print(count_matching_pairs([1, 1, 2, 3], [2, 1, 3, 1]))  # 0, 1, 2, 4
print(count_matching_pairs([1, 20, 30, 1, 40, 1], [2, 1, 3, 4, 1, 5]))  # 0, 1, 1, 1, 2, 2
print(count_matching_pairs([1], [1]))  # 0
print(count_matching_pairs([1, 3, 5], [2, 4, 6]))  # 0, 0, 0
print(count_matching_pairs([1, 3, 5], [4, 5, 3]))  # 0, 0, 2
