# Первые n строк - описание наследования классов в следующем формате.
# <имя класса 1> : <имя класса 2> <имя класса 3> ... <имя класса k>
# Это означает, что класс 1 отнаследован от класса 2, класса 3, и т. д.

# В следующих q строках содержится описание запросов в формате <имя класса 1> <имя класса 2>
# Для каждого запроса выведите в отдельной строке слово "Yes", если класс 1 является предком класса 2, и "No", если не является.

# Sample Input:
#
# 4
# A
# B : A
# C : A
# D : B C
# 4
# A B
# B D
# C D
# D A

# Sample Output:
#
# Yes
# Yes
# Yes
# No


def create_hierarchy(hierarchy=None):
    n = int(input())
    if hierarchy is None:
        hierarchy = dict()

    for _ in range(n):
        a = input().split()
        hierarchy[a[0]] = [] if len(a) == 1 else a[2:]

    return hierarchy


def is_parent(child, parent, hierarchy):
    if child == parent:
        return True

    for p in hierarchy[child]:
        if is_parent(p, parent, hierarchy):
            return True

    return False


# Exclude this execution from imports
if __name__ == "__main__":
    print("I'm in main")
    nodes = create_hierarchy()

    q = int(input())
    for _ in range(q):
        a, b = input().split()
        print("Yes" if is_parent(b, a, nodes) else "No")

else:
    print(f"{__name__} imported")
