# single line comment
"""
multiline
comment
"""

a = "hello1"
b = "hello2"
c = """hello3
world
"""
d = """hello4
world
"""

print(a)
print(b)
print(c)
print(len(d))
print(a * 2)

print("\n======== Character methods ord() and chr() ========")
print(ord("A"))  # 65
print(ord("Ы"))  # 1067
print(ord("✔"))  # 10004

print(chr(65))  # A
print(chr(1067))  # Ы
print(chr(10004))  # ✔

print("\n======== String methods isdigit(), isnumeric(), isdecimal() ========")
print("123".isdigit())  # True
print("²³".isdigit())  # True (superscript digits)
print("123.45".isdigit())  # False (contains a period)
print("-123".isdigit())  # False (contains a sign)

print()
print("123".isnumeric())  # True
print("½".isnumeric())  # True (fraction)
print("Ⅻ".isnumeric())  # True (Roman numeral)
print("12.3".isnumeric())  # False
print("-123".isnumeric())  # False

print()
print("123".isdecimal())  # True
print("12.3".isdecimal())  # False
print("²".isdecimal())  # False (superscript digit)
print("Ⅻ".isdecimal())  # False
print("½".isdecimal())  # False (fraction)
print("-123".isdecimal())  # False (contains a sign)

# Типичный способ в Python проверить, является ли строка числом
def is_signed_number(s: str) -> bool:
    try:
        float(s)
        return True
    except ValueError:
        return False

print()
print(is_signed_number("-12.3"))  # True
print(is_signed_number("Ⅻ"))  # False
print(is_signed_number("½"))  # False
print(is_signed_number("²"))  # False

genome = "acggtgttat"

cCount = genome.upper().count("C")
gCount = genome.upper().count("G")
percent = len(genome) / 100

print((gCount + cCount) / percent)

print("================ slicing ================")
dna = "ATTCGGAGCT"
print("dna=", dna)
print("dna[1]=", dna[1])  # char 1
print("dna[1:4]=", dna[1:4])  # from 1 to 4
print("dna[:4]=", dna[:4])  # from 0 to 4
print("dna[4:]=", dna[4:])  # from 4 to the end
print("dna[1:-1:2]=", dna[1:-1:2])  # from 1 to -1 with step 2
print("dna[::-1]=", dna[::-1])  # reverse (step -1)
print("dna[-100:100]=", dna[-100:100])  # no error, just whole string


def split_every_n_chars(s: str, n: int) -> list[str]:
    """Splits a string into parts of n characters"""
    return [s[i:i+n] for i in range(0, len(s), n)]


def insert_every_n_chars(s: str, n: int, sep: str = "-") -> str:
    """Splits a string into parts of n characters, inserts a separator between them and joins them"""
    return sep.join(s[i:i+n] for i in range(0, len(s), n))


def insert_every_n_chars_from_end(s: str, n: int, sep: str = "-") -> str:
    """
    Backward version of insert_every_n_chars
    Need when we need first part with float length
    """
    parts = []
    i = len(s)
    while i > 0:
        parts.append(s[max(0, i-n):i])
        i -= n
    return sep.join(reversed(parts))


s = "0123456789"
print(split_every_n_chars(s, 3))
print(insert_every_n_chars(s, 3))
print(insert_every_n_chars(s, 3, "#"))


print("================== reverse string ================")

print(''.join(reversed("abcdefg")))


def reverse_str(s: str, k: int) -> str:
    result = list(s)

    for i in range(0, len(s), 2 * k):
        # Reverse first k characters in each 2k block by slice assignment.
        # 'reversed' returns an iterator, but slice accepts any iterable.
        result[i:i+k] = reversed(result[i:i+k])

    return "".join(result)


print(reverse_str("abcdefg", 2))
