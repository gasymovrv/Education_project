import pathlib
import py_compile


def check_syntax(directory):
    files = list(pathlib.Path(directory).rglob("*.py"))
    print(f"Checking syntax of {len(files)} files in {directory}...")

    total_errors = 0
    for path in files:
        try:
            py_compile.compile(str(path), doraise=True)
        except py_compile.PyCompileError as e:
            total_errors += 1
            print(f"❌ {path} has syntax error:\n{e.msg}")

    if total_errors:
        print(f"❌ Total errors found: {total_errors}")
    else:
        print("🎯 All files are valid!")

print("========================================================================================")
check_syntax("./stepikmiddle")
print("\n========================================================================================")
check_syntax("./stepikbase")
print("\n========================================================================================")
check_syntax("./other_examples")
print("\n========================================================================================")
check_syntax("./algorithms")

