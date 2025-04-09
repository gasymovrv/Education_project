class MyHashSet:

    def __init__(self):
        self.data: list[list[object]] = []
        self.count = 0
        self.size = 2  # just for testing, usually it's greater

        for i in range(self.size):
            self.data.append([])
        print("debug---init: ", self.data)

    def add(self, key: object) -> None:
        i = hash(key) % self.size

        if self._find_in_bucket(i, key) < 0:
            self.data[i].append(key)
            self.count += 1

        if self.count > self.size * 0.75:
            self._resize()

    def remove(self, key: object) -> None:
        i = hash(key) % self.size
        j = self._find_in_bucket(i, key)
        if j >= 0:
            del self.data[i][j]
            self.count -= 1

    def contains(self, key: object) -> bool:
        i = hash(key) % self.size
        return self._find_in_bucket(i, key) >= 0

    def _resize(self):
        new_size = self.size * 2
        self.size = new_size
        new_container = []

        for _ in range(new_size):
            new_container.append([])

        for bucket in self.data:
            for key in bucket:
                i = hash(key) % new_size
                new_container[i].append(key)
        self.data = new_container
        print("debug---resized: ", self.data)

    def _find_in_bucket(self, i, key):
        bucket = self.data[i]
        for j in range(len(bucket)):
            if bucket[j] == key:
                return j
        return -1

    def __str__(self):
        str_res = []
        for bucket in self.data:
            for key in bucket:
                str_res.append(key)
        return str(str_res)


set1 = MyHashSet()
set1.add(1)
set1.add(2)
set1.add(2)
set1.add(2)
set1.add(3)
set1.add(4)
set1.add(4)
set1.add(4)
set1.add(5)
print(set1)

set1.remove(4)
print(set1)

print(set1.contains(4))
print(set1.contains(5))


class MyBadObj:
    def __init__(self, value):
        self.value = value

    def __eq__(self, other):
        return self.value == other

    def __hash__(self):
        return 1

    def __repr__(self):
        return str(self.value)


set2 = MyHashSet()
set2.add(MyBadObj(1))
set2.add(MyBadObj(2))
set2.add(MyBadObj(2))
set2.add(MyBadObj(2))
set2.add(MyBadObj(3))
print(set2.data)
