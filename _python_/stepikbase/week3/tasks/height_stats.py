from codecs import open as codecs_open
from statistics import mean

height_by_classes = {}
for cl in range(1, 12):
    height_by_classes[cl] = []

with codecs_open("../test_files/heights.txt", encoding="utf-8") as inf:
    for line in inf:
        line_arr = line.strip().split("\t")
        height_by_classes[int(line_arr[0])].append(int(line_arr[2]))

classes = [i for i in range(1, 12)]
with codecs_open("../test_files/heights-stats.txt", "w", encoding="utf-8") as ouf:
    for cl in classes:
        heights = height_by_classes[cl]
        if len(heights) > 0:
            avg = float(mean(heights))
        else:
            avg = "-"
        ouf.write(f"{cl} {avg}\n")

# remove("../test_files/heights-stats.txt")
