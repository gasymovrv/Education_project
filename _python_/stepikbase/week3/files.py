import os

inf = open("test_files/encoded.txt", "r")  # Чтение файла
s1 = inf.readline()
s2 = inf.readline()
inf.close()  # здесь файл уже закрыт

with open("test_files/encoded.txt") as inf:  # Чтение файла
    for line in inf:
        line = line.strip()
        print(line)
# здесь файл уже закрыт

ouf = open("test_files/file.txt", "w")  # Перезапись/создание файла
ouf.write("Some text\n")
ouf.write(str(25))
ouf.close()

with open("test_files/text.txt", "w") as ouf:  # Перезапись/создание файла
    ouf.write("Some text\n")
    ouf.write(str(25) + "\n")

with open("test_files/text.txt", "a") as ouf:  # Добавление в конец файла
    ouf.write("\nappended text")
    # myfile.write(1) # ошибка, принимает только строки

os.remove("test_files/file.txt")
os.remove("test_files/text.txt")
