from collections import deque


def shortest_path(graph, from_, to):
    """
    Breadth First Search (BFS)
    Complexity: O(V + E) where:
    V is the number of vertices (keys in graph) and
    E is the number of edges (elements in values of graph)
    """
    q = deque()
    q.append((from_, 0))
    visited = set()

    while q:
        node, depth = q.popleft()
        if node in visited:
            continue
        visited.add(node)

        for neighbor in graph.get(node, []):
            if neighbor == to:
                return depth + 1
            q.append((neighbor, depth + 1))

    return -1


graph1 = {
    "S": ["1", "2"],
    "1": ["F", "3"],
    "2": ["3", "4"],
    "4": ["F"],
    "F": []
}

graph2 = {
    "cab": ["car", "cat"],
    "car": ["cat", "bar"],
    "mat": ["bat"],
    "cat": ["mat", "bat"],
    "bar": ["bat"],
    "bat": []
}

print(shortest_path(graph1, 'S', 'F'))  # expected 2
print(shortest_path(graph2, 'cab', 'bat'))  # expected 2


def cheapest_path(graph, from_, to):
    """
    Dijkstra’s algorithm is used to calculate the shortest path for a weighted graph.
    Only works with directed acyclic graphs (DAG) with positive weights.
    Complexity: O(V^2)
    """

    costs = graph[from_]
    parents = {}
    for n in graph:
        if n == from_:
            continue

        if n not in costs:
            costs[n] = float("inf")
            parents[n] = None
        else:
            parents[n] = from_

    processed = set()

    def find_lowest_cost_node():
        lowest_cost = float("inf")
        lowest_cost_node = None
        for node in costs:
            cost = costs[node]
            if cost < lowest_cost and node not in processed:
                lowest_cost = cost
                lowest_cost_node = node
        return lowest_cost_node

    node = find_lowest_cost_node()
    while node is not None:
        cost = costs[node]
        neighbors = graph[node]

        for n in neighbors.keys():
            new_cost = cost + neighbors[n]
            if new_cost < costs[n]:
                costs[n] = new_cost
                parents[n] = node

        processed.add(node)
        node = find_lowest_cost_node()

    path = [to]
    next_ = to
    while next_ != from_:
        next_ = parents[next_]
        path.append(next_)
    return costs[to], "->".join(reversed(path))


graph3 = {
    "START": {"A": 6, "B": 2},
    "A": {"FIN": 1},
    "B": {"A": 3, "FIN": 5},
    "FIN": {}
}
print(cheapest_path(graph3, "START", "FIN"))
