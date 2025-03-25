class Node:
    def __init__(self, name: str, children: list["Node"] = None):
        if children is None or not isinstance(children, list):
            children = []

        self.name = name
        self.children = children

    def __repr__(self):
        return f"(name: {self.name}, children: {self.children})"

    def __eq__(self, other):
        return self.name == other.name

    def has_child(self, child_name):
        if self.name == child_name:
            return True
        if self.children is None:
            return False

        result = False
        for child in self.children:
            if child.name == child_name:
                return True
            result = child.has_child(child_name)
            if result:
                return True

        return result

    def add_child(self, child: "Node"):
        self.children.append(child)

    def add_children(self, children: list["Node"]):
        self.children.extend(children)

    def count_children(self, visited=None):
        if visited is None:
            visited = set()

        if self.name in visited:
            return 0

        visited.add(self.name)

        if not self.children:
            return 1

        count = 1
        for child in self.children:
            count += child.count_children(visited)

        return count


class BiNode(Node):
    def __init__(self, name: str,
                 children: list["BiNode"] = None,
                 parents: list["BiNode"] = None):
        super().__init__(name, children)
        if parents is None or not isinstance(parents, list):
            parents = []
        self.parents = parents

    def has_parent(self, p_name: str):
        if self.name == p_name:
            return True
        if self.parents is None:
            return False

        result = False
        for p in self.parents:
            if p.name == p_name:
                return True
            result = p.has_parent(p_name)
            if result:
                return True

        return result

    def has_any_parent(self, p_names: list[str]):
        for p_name in p_names:
            if self.has_parent(p_name):
                return True

    def add_parent(self, p: "BiNode"):
        self.parents.append(p)

    def add_parents(self, ps: list["BiNode"]):
        self.parents.extend(ps)
