import json


def parse_json_inheritance(data: str) -> list[tuple[str, int]]:
    data = json.loads(data)
    parents_with_children = {}

    for item in data:
        child = item["name"]
        if child not in parents_with_children:
            parents_with_children[child] = set()

        for parent in item["parents"]:
            if parent not in parents_with_children:
                parents_with_children[parent] = set()
            parents_with_children[parent].add(child)

    # Recursive DFS traversal through all descendants (children at each level)
    def dfs_count_descendants(parent: str, visited: set = None):
        if visited is None:
            visited = set()
        if parent not in parents_with_children:
            return 0

        visited.add(parent)
        count = 1

        for child in parents_with_children[parent]:
            if child not in visited:
                count += dfs_count_descendants(child, visited)

        return count

    # Create sorted list of tuple with parent name and count of descendants
    return sorted(map(
        lambda item: (
            item["name"],
            dfs_count_descendants(item["name"])
        ),
        data
    ))


result = parse_json_inheritance(input())
for item in result:
    print(f"{item[0]} : {item[1]}")

# Нужно посчитать число потомков для каждого родителя

# Sample Input 1:
# [{"name": "A", "parents": []}, {"name": "B", "parents": ["A", "C"]}, {"name": "C", "parents": ["A"]}]

# Sample Output 2:
# A : 3
# B : 1
# C : 2

# Sample Input 2:
# [{"name": "B", "parents": ["A", "C"]}, {"name": "C", "parents": ["A"]}, {"name": "A", "parents": []}, {"name": "D", "parents":["C", "F"]}, {"name": "E", "parents":["D"]}, {"name": "F", "parents":[]}]

# Sample Output 2:
# A : 5
# B : 1
# C : 4
# D : 2
# E : 1
# F : 3
