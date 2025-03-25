
# r (read) - открыть для чтения (по умолчанию)
# w (write) - открыть для записи, содержимое файла стирается
# a (append) - открыть для записи, запись ведется в конец
f = open("tests/test_append.txt", "a")
f.write("Hello\n")

f.close()
