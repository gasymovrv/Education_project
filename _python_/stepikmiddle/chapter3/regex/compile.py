import re
import timeit

pattern = r"\d+"
compiled = re.compile(pattern)
string = "123abc" * 1000

# Without compile
print("Without re.compile:", timeit.timeit(lambda: re.match(pattern, string), number=900000))

# With compile
print("With re.compile:", timeit.timeit(lambda: compiled.match(string), number=900000))
