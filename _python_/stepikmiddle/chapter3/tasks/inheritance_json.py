import json

from stepikmiddle.chapter1.tasks.tree_nodes import Node


def parse_json_inheritance(data: str) -> list[tuple[str, int]]:
    data = json.loads(data)
    hierarchy = {}

    for item in data:
        child_name = item["name"]
        child = hierarchy.get(child_name)
        if not child:
            child = Node(child_name)
            hierarchy[child_name] = child

        for parent_name in item["parents"]:
            p_node = hierarchy.get(parent_name)
            if not p_node:
                p_node = Node(parent_name, children=[child])
                hierarchy[parent_name] = p_node
            else:
                p_node.add_child(child)

    counter = {}
    for name, node in hierarchy.items():
        counter[name] = node.count_children()

    return sorted(counter.items())


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
