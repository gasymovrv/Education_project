import heapq

# -------------- Min-heap example:
heap = []
heapq.heappush(heap, 10)
heapq.heappush(heap, 20)
heapq.heappush(heap, 5)

print(heap)  # Output: [5, 20, 10]

# Get the smallest item from the heap without popping it:
smallest_in_heap = heap[0]
print(smallest_in_heap)  # Output: 5

# Pop the smallest item (5 in this case):
smallest = heapq.heappop(heap)
print(smallest)  # Output: 5
print(heap)      # Output: [10, 20]


# --------- Max-heap example:
max_heap = []
heapq.heappush(max_heap, -10)
heapq.heappush(max_heap, -20)
heapq.heappush(max_heap, -5)

print(max_heap)  # Output: [-20, -10, -5]

# Get the largest item from the heap without popping it:
largest_in_heap = max_heap[0]
print(-largest_in_heap)  # Output: 20

# Pop the largest item (20 in this case):
largest = heapq.heappop(max_heap)
print(-largest)  # Output: 20
print(max_heap)  # Output: [-10, -5]


# -------------- Using heapify to convert a list into a heap:
data = [20, 10, 30, 5]
heapq.heapify(data)
print(data)  # Output: [5, 10, 30, 20]


# ------------ Using nlargest and nsmallest to get the largest and smallest elements:
numbers = [10, 30, 20, 50, 40, 5]

# Get the 3 largest numbers
largest = heapq.nlargest(3, numbers)
print(largest)  # Output: [50, 40, 30]

# Get the 3 smallest numbers
smallest = heapq.nsmallest(3, numbers)
print(smallest)  # Output: [5, 10, 20]


# -------------- Priority queue example:
class PriorityQueue:
    def __init__(self):
        self._queue = []
        self._index = 0

    def enqueue(self, item, priority):
        """
        Add an item to the priority queue with the given priority.
        Items with higher priority are dequeued first.
        If two items have the same priority, they are dequeued in the order they were added.
        """
        heapq.heappush(self._queue, (-priority, self._index, item))
        self._index += 1

    def dequeue(self):
        _, _, item = heapq.heappop(self._queue)
        return item


priority_queue = PriorityQueue()
priority_queue.enqueue("Task 1", 3)
priority_queue.enqueue("Task 2", 1)
priority_queue.enqueue("Task 3", 2)

print(priority_queue.dequeue())  # Output: "Task 2"
print(priority_queue.dequeue())  # Output: "Task 3"
print(priority_queue.dequeue())  # Output: "Task 1"
