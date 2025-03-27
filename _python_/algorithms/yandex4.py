def monotonic_subarray(arr: list[int]) -> list[int]:
    """
    Дан массив целых чисел nums. Нужно найти самый большой строго монотонный отрезок (отрезок, у которого все
    элементы строго убывают или строго возрастают).
    Вернуть индексы первого и последнего элементов этого отрезка.
    """
    inc_len = 1
    dec_len = 1
    max_len = 1
    subarr_start = -1
    subarr_end = -1

    for i in range(1, len(arr)):
        if arr[i] > arr[i - 1]:
            inc_len += 1
            dec_len = 1
        elif arr[i - 1] > arr[i]:
            dec_len += 1
            inc_len = 1

        if inc_len >= max_len and inc_len >= dec_len:
            subarr_start = (i - inc_len) + 1
            subarr_end = i
            max_len = inc_len

        if dec_len > max_len and dec_len > inc_len:
            subarr_start = (i - dec_len) + 1
            subarr_end = i
            max_len = dec_len

    return [subarr_start, subarr_end]


print(monotonic_subarray([1, 2, 3, 2, 1, 5, 6, 7, 8, 2, 1]))  # Ожидаемый вывод: [4, 8] (отрезок: 1, 5, 6, 7, 8)
print(monotonic_subarray([1, 2, 3, 2, 1, -5, -6, -7, -60, 2, 1]))  # Ожидаемый вывод: [2, 8] (отрезок: 3, 2, 1, -5, -6, -7, -60)
print(monotonic_subarray([1, 2, 3, 1, 1, 1]))  # Ожидаемый вывод: [0, 2] (отрезок: 1, 2, 3)
