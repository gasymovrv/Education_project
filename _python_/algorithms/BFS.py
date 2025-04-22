from collections import deque


def shortest_path(graph, from_, to):
    """
    Breadth First Search (BFS) algorithm
    Here, we use BFS to find the shortest path between two nodes in a graph.

    Complexity of BFS: O(V + E) where:
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
