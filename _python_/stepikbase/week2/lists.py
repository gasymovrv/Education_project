students = ["Ivan", "Masha", "Sasha"]
students += ["Olga"]
students.append("Yana")
students += "ABcd"
students[3] = "Oleg"
print("students =", students)

repeated = ["Repeated"] * 3
print("repeated =", repeated)
wrong_matrix = [[1] * 2] * 2  # wrong, because it does a copy of reference to the same object
wrong_matrix[0][0] = 2  # it also changes the first element of the second row
print("wrong_matrix creation =", wrong_matrix)  # [[2, 1], [2, 1]]

correct_matrix = [[1] * 2 for _ in range(2)]
correct_matrix[0][0] = 2
print("correct_matrix creation =", correct_matrix)

students2 = students * 3  # copy and repeat
print("students * 3 =", students2)

students.insert(1, "Dima")
print("students.insert(1, 'Dima'): ", students)

students.remove("Sasha")
print("students.remove('Sasha'):", students)

students.pop()
print("students.pop():", students)

del students[1]
print("del students[1]:", students)

for student in students:
    print("Hello, " + student + "!")

print("len(students) =", len(students))
print("students[::-1] =", students[::-1])  # индексация и срезы на списках работают также, как и со строками
print("students[1:3] =", students[1:3])
print("students[1:] =", students[1:])
print("students[:3] =", students[:3])
print("students[:] =", students[:])

print("Ivan in students =", "Ivan" in students)
print("Petya in students =", "Petya" not in students)

print("students.index(\"Ivan\") =", students.index("Ivan"))
print("students.count(\"Ivan\") =", students.count("Ivan"))

students3 = students  # direct link
students4 = students * 1  # copy
students[1] = "Masha2"
print("changed students =", students)
print("students3 =", students3)
print("copied students4 =", students4)

students4.sort()
print("students4.sort():", students4)
print("sorted(students) =", sorted(students))
print("ignore case sorted(students) =", sorted(students, key=lambda x: x.casefold()))

print("max(students) =", max(students))
print("ignore case max(students) =", max(students, key=lambda x: x.casefold()))
print("min(students) =", min(students))

generated_array = [i for i in range(10)]
print("generated_array =", generated_array)

a, b, c = [i for i in range(1, 4)]
print(f"destructed array: a = {a}, b = {b}, c = {c}")
