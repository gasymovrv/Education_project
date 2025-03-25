def avg(arr):
    if len(arr) == 0:
        return 0
    return sum(arr) / len(arr)


def count_words(words_str, words_dict):
    words_str = words_str.split()
    for word in words_str:
        if word.lower() in words_dict:
            words_dict[word.lower()] += 1
        else:
            words_dict[word.lower()] = 1


def decode_duplicates(encoded_str):
    if encoded_str == "":
        return ""

    decoded_str = ""
    last_ch = None
    duplicate_counter = ""

    for i, ch in enumerate(encoded_str):
        if ch.isnumeric():
            duplicate_counter += ch

        if (not ch.isnumeric() and last_ch != ch and duplicate_counter != "") or i == len(encoded_str) - 1:
            decoded_str += str(last_ch) * int(duplicate_counter)
            duplicate_counter = ""

        if not ch.isnumeric():
            last_ch = ch

    return decoded_str
