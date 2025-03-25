print(f"Executed pack1/__init__.py: {__name__}")


def init_fuc():
    print("init_fuc")


def _f4_func():
    print("Excluded from * imports because it starts with underscore")
