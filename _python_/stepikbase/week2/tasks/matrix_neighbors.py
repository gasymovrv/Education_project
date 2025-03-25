matrix = []

while True:
    inp = input()
    if inp == "end":
        break
    row = [int(ch) for ch in inp.split()]
    matrix.append(row)

neighbors_sum_matrix = []

for i in range(len(matrix)):
    neighbors_sum_matrix.append([])

    for j in range(len(matrix[i])):
        up = matrix[i - 1][j]

        if i == len(matrix) - 1:
            down = matrix[0][j]
        else:
            down = matrix[i + 1][j]

        left = matrix[i][j - 1]

        if j == len(matrix[i]) - 1:
            right = matrix[i][0]
        else:
            right = matrix[i][j + 1]

        neighbors_sum_matrix[i].insert(j, up + down + left + right)

    print(*neighbors_sum_matrix[i])
