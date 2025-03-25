words = input().split()

words_dict = {}
for word in words:
    if word.lower() in words_dict:
        words_dict[word.lower()] += 1
    else:
        words_dict[word.lower()] = 1

for key, value in words_dict.items():
    print(key, value)
