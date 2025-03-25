with open("tests/test.txt") as f:
    for line in f:
        print(line.rstrip())

with open("tests/test.txt") as f, open("tests/test_copy.txt", "w") as w:
    for line in f:
        w.write(line)
