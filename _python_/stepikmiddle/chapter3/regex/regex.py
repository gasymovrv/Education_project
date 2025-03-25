import re

# [] -- можно указать множество подходящих символов
# . ^ $ * + ? { } [ ] \ | ( ) -- метасимволы
# \d ~ [0-9] -- цифры
# \D ~ [^0-9]
# \s ~ [ \t\n\r\f\v] -- пробельные символы
# \S ~ [^ \t\n\r\f\v]
# \w ~ [a-zA-Z0-9_] -- буквы + цифры + _
# \W ~ [^a-zA-Z0-9_]

print("\nraw strings")  # raw строки будут использоваться для написания регулярных выражений
x = r"hello \n world"  # raw
print(x)

print("\nmatch")
pattern = r"abc"
# match проверяет, соответствует ли строка регулярному выражению. Если не указаны метасимволы, то проверяется тупо как startswith
match_object = re.match(pattern, "abcd")  # <re.Match object; span=(0, 3), match='abc'>
print(match_object)

match_object = re.match(pattern, "acc")  # None
print(match_object)

match_object = re.match(pattern, "dabc")  # None
print(match_object)

pattern = r"a.c"
string = "acc"
match_object = re.match(pattern, string)
print(match_object)

# компилируем регулярное выражение заранее. Это ускоряет работу если match/search/findall и др. вызываются много раз
print("\ncompile")
pattern = re.compile(r"a[ab]+a")
print(pattern)
print(re.match(pattern, "abbbaasdg"))

print("\nsearch")
match_object = re.search(pattern, "dabcd")
print(match_object)

print("\nfindall")
string = "abc a.c aac a-c aBc azc"
all_inclusions = re.findall(pattern, string)
print(all_inclusions)

print("\nsub")
fixed_typos = re.sub(pattern, "abc", string)
print(fixed_typos)

print("\nescape meta characters")
pattern = r" english\?"
string = "Do you speak english?"
match = re.search(pattern, string)
print(match)

print("\nrepetition quantifiers")
pattern = r"ab{2,4}a"
string = "aa, aba, abba, abbba, abbbba"
all_inclusions = re.findall(pattern, string)
print(all_inclusions)

pattern = r"a[ab]+a"  # Жадный
string = "abaaba"
print(re.match(pattern, string))
print(re.findall(pattern, string))

pattern = r"a[ab]+?a"  # Нежадный
string = "abaaba"
print(re.match(pattern, string))
print(re.findall(pattern, string))

pattern = r"(test|text)"
string = "test-text"
print(f"\ngrouping, pattern={repr(pattern)} s={repr(string)}")
match = re.match(pattern, string)
print(match.groups())
print(re.findall(pattern, string))

pattern = r"((abc)|(test|text)*)"  # 1 группа самая большая, затем вложенные
string = "abc"
print(f"\ngrouping, pattern={repr(pattern)} s={repr(string)}")
match = re.match(pattern, string)
print(match.groups())

pattern = r"((abc)|(test|text)*)"
string = "testtext"
print(f"\ngrouping, pattern={repr(pattern)} s={repr(string)}")
match = re.match(pattern, string)
print(match.groups())

pattern = r"Hello (abc|test)"
string = "Hello abc"
print(f"\ngrouping, pattern={repr(pattern)} s={repr(string)}")
match = re.match(pattern, string)
print(match)
print(match.group())
print(match.group(0))
print(match.group(1))
print(re.findall(pattern, string))

pattern = r"(\w+)-\1"  # переиспользование группы, чтобы найти повторяющееся через дефис слова
string = "test-test chow-chow text"
print(f"\nreuse grouping, pattern={repr(pattern)} s={repr(string)}")
duplicates = re.sub(pattern, r"\1", string)  # заменить соответствие паттерну на первую группу, т.е. убрать дубликаты
print(duplicates)

pattern = r"(\w+)-[\d]+"
string = "test-123 chow-124 text-1235"
print(f"\nreuse grouping, pattern={repr(pattern)} s={repr(string)}")
duplicates = re.sub(pattern, r"\1-\1", string)  # заменить слово-<цифры> на слово-слово
print(duplicates)

pattern = r"((\w+)-\2)"  # 2 группы, найти пары слово-слово
string = "test-test chow-chow text"
print(f"\nreuse grouping, pattern={repr(pattern)} s={repr(string)}")
duplicates = re.findall(pattern, string)
print(duplicates)

print("\nflags")
x = re.match(r"(te)*?xt", "TEXT", re.IGNORECASE | re.DEBUG)  # Флаги передаются как побитовое ИЛИ
print(x)
