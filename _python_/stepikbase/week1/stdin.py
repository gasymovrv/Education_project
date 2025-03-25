# print(input("Введите данные"))
input_minutes = int(input())
start_hours = int(input())
start_minutes = int(input())
input_minutes = input_minutes + start_minutes

alarm_hours = start_hours + (input_minutes // 60)
alarm_minutes = input_minutes % 60
print(alarm_hours)
print(alarm_minutes)
