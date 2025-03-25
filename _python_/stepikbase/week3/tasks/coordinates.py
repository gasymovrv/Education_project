n = int(input())

commands = {}
for i in range(n):
    line = input().split()
    cmd_name = line[0]
    if cmd_name not in commands:
        commands[cmd_name] = int(line[1])
    else:
        commands[cmd_name] += int(line[1])

final_coordinates = [0, 0]
for key, value in commands.items():
    if key == "восток":
        final_coordinates[0] += value
    elif key == "север":
        final_coordinates[1] += value
    elif key == "запад":
        final_coordinates[0] -= value
    elif key == "юг":
        final_coordinates[1] -= value

print(*final_coordinates, sep=" ")
