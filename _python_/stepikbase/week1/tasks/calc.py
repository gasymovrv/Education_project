a = float(input())
b = float(input())
operation = input()

if operation == "+":
    print(a + b)
elif operation == "-":
    print(a - b)
elif operation == "*":
    print(a * b)
elif operation == "/":
    if b == 0:
        print("Деление на 0!")
    else:
        print(a / b)
elif operation == "mod":
    if b == 0:
        print("Деление на 0!")
    else:
        print(a % b)
elif operation == "pow":
    print(a ** b)
elif operation == "div":
    if b == 0:
        print("Деление на 0!")
    else:
        print(a // b)
