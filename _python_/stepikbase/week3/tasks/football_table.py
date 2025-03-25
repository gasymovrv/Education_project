n = int(input())

table = {}
for i in range(n):
    line = input().split(";")

    team1 = line[0]
    team2 = line[2]
    if team1 not in table:
        table[team1] = [0] * 5
    if team2 not in table:
        table[team2] = [0] * 5

    score1 = int(line[1])
    score2 = int(line[3])

    # количество матчей
    table[team1][0] += 1
    table[team2][0] += 1

    if score1 > score2:
        table[team1][1] += 1  # количество побед команды 1
        table[team1][4] += 3  # количество очков команды 1
        table[team2][3] += 1  # количество поражений команды 2
    elif score2 > score1:
        table[team2][1] += 1  # количество побед команды 2
        table[team2][4] += 3  # количество очков команды 2
        table[team1][3] += 1  # количество поражений команды 1
    else:
        table[team1][2] += 1  # количество ничьих команды 1
        table[team1][4] += 1  # количество очков команды 1
        table[team2][2] += 1  # количество ничьих команды 2
        table[team2][4] += 1  # количество очков команды 2

for key, value in table.items():
    print(f"{key}:", end="")
    print(*value, sep=" ")
