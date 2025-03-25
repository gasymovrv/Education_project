def count_matching_pairs(nums1, nums2):
    from collections import Counter

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
