def count_replaces(s: str, a: str, b: str) -> int | str:
    if a not in s:
        return 0
    elif a in b:
        return "Impossible"

    for i in range(1000):
        s = s.replace(a, b)
        if a not in s:
            return i + 1

    return "Impossible"


# print(count_replaces(input(), input(), input()))

print(count_replaces("abab", "ab", "ba"))
print(count_replaces("ababa", "a", "b"))
print(count_replaces("ababa", "b", "a"))
print(count_replaces("ababa", "c", "c"))
print(count_replaces("ababac", "c", "c"))
