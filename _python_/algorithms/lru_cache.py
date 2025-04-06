from collections import OrderedDict


class LRUCache:
    """
    Least recently used cache.
    If size is exceeded, the least recently used item is removed.
    """

    def __init__(self, capacity: int):
        self._cache = OrderedDict()
        self._capacity = capacity

    def get(self, key: int) -> int:
        if key in self._cache:
            self._cache.move_to_end(key)  # Mark as recently used
            return self._cache[key]
        return -1

    def put(self, key: int, value: int) -> None:
        if key in self._cache:
            self._cache.move_to_end(key)

        self._cache[key] = value

        if len(self._cache) > self._capacity:
            self._cache.popitem(last=False)  # Remove least recently used item

    def __repr__(self):
        return str(self._cache)


lru_cache = LRUCache(2)
lru_cache.put(1, 1)
lru_cache.put(2, 2)
print(lru_cache)

lru_cache.get(1)
print(lru_cache)

lru_cache.put(3, 3)
print(lru_cache)

lru_cache.get(2)
lru_cache.put(4, 4)
print(lru_cache)

lru_cache.get(1)
lru_cache.get(3)
lru_cache.get(4)

print(lru_cache)
