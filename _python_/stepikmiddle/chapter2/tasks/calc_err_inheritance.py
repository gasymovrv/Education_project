from stepikmiddle.chapter1.tasks.calc_inheritance import create_hierarchy

# В первой строке входных данных содержится целое число n -
# число классов исключений.

# В следующих n строках содержится описание наследования классов.
# В i-й строке указано от каких классов наследуется i-й класс.
# Обратите внимание, что класс может ни от кого не наследоваться.
# Гарантируется, что класс не наследуется сам от себя (прямо или косвенно),
# что класс не наследуется явно от одного класса более одного раза.

# Выведите в отдельной строке имя каждого исключения, обработку которого можно удалить из кода,
# не изменив при этом поведение программы.
# Имена следует выводить в том же порядке, в котором они идут во входных данных.

# Sample Input:
# 4
# ArithmeticError
# ZeroDivisionError : ArithmeticError
# OSError
# FileNotFoundError : OSError
# 4
# ZeroDivisionError
# OSError
# ArithmeticError
# FileNotFoundError

# Sample Output:
# FileNotFoundError


nodes = create_hierarchy(bi_node=True)
q = int(input())

caught_errors = []
useless_errors = []
for i in range(q):
    err_class = input()
    caught_errors.append(err_class)
    node = nodes.get(err_class)
    if i != 0 and node.has_any_parent(caught_errors[0:i]):
        useless_errors.append(err_class)

for r in useless_errors:
    print(r)
