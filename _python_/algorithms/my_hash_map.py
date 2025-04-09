from typing import Optional


class MyHashMap:

    def __init__(self):
        self.data: list[list[tuple[object, object]]] = []
        self.count = 0
        self.size = 2  # just for testing, usually it's greater

        for i in range(self.size):
            self.data.append([])

    def put(self, key: object, value: object) -> None:
        i = hash(key) % self.size
        j = self._find_in_bucket(i, key)
        item = (key, value)

        if j < 0:
            self.data[i].append(item)
            self.count += 1
        else:
            self.data[i][j] = item

        if self.count > self.size * 0.75:
            self._resize()

    def get(self, key: object) -> Optional[object]:
        i = hash(key) % self.size
        j = self._find_in_bucket(i, key)

        if j >= 0:
            return self.data[i][j][1]

        return None

    def remove(self, key: object) -> None:
        i = hash(key) % self.size
        j = self._find_in_bucket(i, key)
        if j >= 0:
            del self.data[i][j]
            self.count -= 1

    def _resize(self):
        new_size = self.size * 2
        self.size = new_size
        new_container = []

        for _ in range(new_size):
            new_container.append([])

        for bucket in self.data:
            for item in bucket:
                i = hash(item[0]) % new_size
                new_container[i].append(item)
        self.data = new_container

    def _find_in_bucket(self, i, key):
        bucket = self.data[i]
        for j in range(len(bucket)):
            if bucket[j][0] == key:
                return j
        return -1

    def __str__(self):
        str_res = []
        for bucket in self.data:
            for key in bucket:
                str_res.append(key)
        return str(str_res)


map1 = MyHashMap()
map1.put(1, "A")
map1.put(2, "B")
map1.put(2, "B2")
map1.put(3, "C")
print(map1.data)
print(map1)
print(map1.get(1))
print(map1.get(2))
print(map1.get(5))
map1.remove(1)
print(map1.get(1))
print(map1)


class MyBadObj:
    def __init__(self, value):
        self.value = value

    def __eq__(self, other):
        return self.value == other

    def __hash__(self):
        return 1

    def __repr__(self):
        return str(self.value)


map2 = MyHashMap()
map2.put(MyBadObj(1), "A")
map2.put(MyBadObj(2), "B")
map2.put(MyBadObj(2), "B2")
map2.put(MyBadObj(3), "C")
print(map2.data)  # all objects are in 1 bucket
