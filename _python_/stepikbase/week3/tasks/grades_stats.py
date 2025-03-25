from codecs import open as codecs_open

from stepikbase.week3.my_module import avg

math = []
physics = []
russian = []
grades_by_students = {}

with codecs_open("../test_files/grades.txt", encoding="utf-8") as inf:
    for line in inf:
        line_arr = line.strip().split(";")
        math.append(int(line_arr[1]))
        physics.append(int(line_arr[2]))
        russian.append(int(line_arr[3]))
        grades_by_students[line_arr[0]] = [int(line_arr[1]), int(line_arr[2]), int(line_arr[3])]

with codecs_open("../test_files/grades-stats.txt", "w", encoding="utf-8") as ouf:
    for key, value in grades_by_students.items():
        ouf.write(f"{key}: {str(avg(value))}\n")

    ouf.write("==== Среднее среди всех студентов ====\n")
    ouf.write(f"По математике: {str(avg(math))}\n")
    ouf.write(f"По физике: {str(avg(physics))}\n")
    ouf.write(f"По русскому языку: {str(avg(russian))}\n")

# remove("../test_files/grades-stats.txt")
