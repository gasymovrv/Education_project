def compress(s: str) -> str:
    """
    Написать алгоритм сжатия. То есть приходит строка из латиницы, повторяющиеся символы сократить до символ + число повторений
    Пример:
    Input: AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB
    Output: A4B3C2XYZD4E3F3A6B28
    :return: сжатая строка
    """
    result = []
    count = 1
    letter = s[0]

    for i in range(1, len(s)):
        if s[i] == letter:
            count += 1
        else:
            result.append(letter)
            if count > 1:
                result.append(str(count))
            letter = s[i]
            count = 1

    result.append(letter)
    if count > 1:
        result.append(str(count))

    return "".join(result)

print(compress("AAAABBBCCXYZDDDDEEEFFFAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBB"))
