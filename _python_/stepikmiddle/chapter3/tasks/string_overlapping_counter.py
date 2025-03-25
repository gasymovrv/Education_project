def count_overlapping(s: str, t: str) -> int:
    index = s.find(t)
    if index == -1:
        return 0

    count = 0
    while index != -1:
        count += 1
        index = s.find(t, index + 1)

    return count


# print(count_overlapping(input(), input()))

print(count_overlapping("abababa", "aba"))  # 3
print(count_overlapping("abababa", "abc"))  # 0
print(count_overlapping("abc", "abc"))  # 1
print(count_overlapping("aaaaa", "a"))  # 5
