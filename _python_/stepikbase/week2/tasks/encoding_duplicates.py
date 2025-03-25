s = input()

duplicate_counter = 1
encoded_str = ""
last_ch = s[0]

for ch in s[1:]:
    if last_ch == ch:
        duplicate_counter += 1

    if last_ch != ch:
        encoded_str += last_ch + str(duplicate_counter)
        duplicate_counter = 1
    last_ch = ch

encoded_str += last_ch + str(duplicate_counter)

print(encoded_str)
