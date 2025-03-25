from collections import deque

# Why Use deque Instead of a List?
#
#     Lists in Python are efficient for appending at the end but slow for inserting or popping from the front.
#     deque is faster for both ends (O(1) complexity for append and pop operations at both ends).

# append(x)	Add an element to the right end
# appendleft(x)	Add an element to the left end
# pop()	Remove and return an element from the right end
# popleft()	Remove and return an element from the left end
# extend(iterable)	Add multiple elements to the right end
# extendleft(iterable)	Add multiple elements to the left end (in reverse order)
# rotate(n)	Rotate the deque n steps (right if positive, left if negative)
# clear()	Remove all elements
# reverse()	Reverse elements in-place

print("=================== append and pop ===================")
dq = deque()

dq.append(10)  # [10]
dq.append(20)  # [10, 20]
dq.appendleft(5)  # [5, 10, 20]

print(dq)  # deque([5, 10, 20])

dq.pop()  # 20 removed
dq.popleft()  # 5 removed

print(dq)  # deque([10])

print("=================== extend and rotate ===================")
dq = deque([1, 2, 3])
dq.extend([4, 5])  # [1, 2, 3, 4, 5]
dq.extendleft([0, -1])  # [-1, 0, 1, 2, 3, 4, 5]

print(dq)

dq.rotate(2)  # [4, 5, -1, 0, 1, 2, 3]
print(dq)

dq.rotate(-3)  # [0, 1, 2, 3, 4, 5, -1]
print(dq)
