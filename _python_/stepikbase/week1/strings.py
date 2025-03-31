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
