import pathlib

import pycodestyle


def check_pep8(directory):
    style = pycodestyle.StyleGuide(quiet=False, max_line_length=120)
    files = list(pathlib.Path(directory).rglob("*.py"))

    print(f"Checking PEP8 for {len(files)} files in {directory}...")

    result = style.check_files([str(file) for file in files])

    if result.total_errors == 0:
        print("🎯 All files are PEP8 valid!")
    else:
        print(f"❌ Total errors found: {result.total_errors}")

print("========================================================================================")
check_pep8("./stepikmiddle")

print("\n========================================================================================")
check_pep8("./stepikbase")
