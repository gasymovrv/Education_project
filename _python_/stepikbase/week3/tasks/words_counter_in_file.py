from stepikbase.week3.my_module import count_words

dictionary = {}
with open("../test_files/words.txt") as inf:
    for line in inf:
        count_words(line.strip(), dictionary)

max_value = 0
max_key = ""
for key, value in dictionary.items():
    if value > max_value:
        max_value = value
        max_key = key

    if value == max_value and max_key > key:
        max_key = key

# expected: xydcybyxa 7
print(max_key, max_value)
