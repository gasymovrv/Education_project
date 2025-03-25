

def combinations(n, k):
    if k == 0:
        return 1
    if n == 0:
        return 0
    if k > n:
        return 0

    return combinations(n - 1, k) + combinations(n - 1, k - 1)


n, k = map(int, input().split())


print(combinations(n, k))
