# create <namespace> <parent> –  создать новое пространство имен с именем <namespace> внутри пространства <parent>
# add <namespace> <var> – добавить в пространство <namespace> переменную <var>
# get <namespace> <var> – получить имя пространства,
# из которого будет взята переменная <var> при запросе из пространства <namespace>,
# или None, если такого пространства не существует

# Sample Input:
# 9
# add global a
# create foo global
# add foo b
# get foo a
# get foo c
# create bar foo
# add bar a
# get bar a
# get bar b

# Sample Output:
# global
# None
# bar
# foo


# Finds namespace object by name and its parent
# Returns: tuple(parent namespace name, child namespace object)
def get_space(space_name, spaces, parent_name=None):
    if spaces is None:
        return None, None

    spaces.keys()
    for key, value in spaces.items():
        if key == space_name:
            return parent_name, value

        parent_key, space_obj = get_space(space_name, value, parent_name=key)
        if space_obj is not None:
            return parent_key, space_obj
    return parent_name, None


def calculate_space_for_var(var_name, current_space_name, spaces):
    while True:
        parent_name, current_space = get_space(current_space_name, spaces)
        if current_space is None:
            return None
        if var_name in current_space:
            return current_space_name  # found, done
        current_space_name = parent_name


def create(new_space_name, parent_name, spaces):
    _, parent_space = get_space(parent_name, spaces)
    if parent_space is not None:
        parent_space[new_space_name] = {}


def add(var_name, parent_name, spaces):
    _, parent_space = get_space(parent_name, spaces)
    if parent_space is not None:
        parent_space[var_name] = None


namespaces = {
    "global": {}
}

n = int(input())
for _ in range(n):
    cmd, namesp, arg = input().split()
    if cmd == "create":
        create(namesp, arg, namespaces)
    elif cmd == "add":
        add(arg, namesp, namespaces)
    elif cmd == "get":
        print(calculate_space_for_var(arg, namesp, namespaces))
