import os.path
import shutil

print("os.getcwd()=", os.getcwd())  # what is current directory
print("os.listdir()=", os.listdir())  # files and dirs in current directory
print("os.listdir(\"..\")=", os.listdir(".."))  # parent directory
print("os.path.exists(\"./files.py\")=", os.path.exists("./files.py"))
print("os.path.isfile(\"./files.py\")=", os.path.isfile("./files.py"))
print("os.path.isdir(\"./files.py\")=", os.path.isdir("./files.py"))
print("os.path.getsize(\"./files.py\")=", os.path.getsize("./files.py"))
print("os.path.abspath(\"./files.py\")=", os.path.abspath("./files.py"))
print("os.chdir(\"..\")", os.chdir(".."))
print("os.getcwd()=", os.getcwd())

print("os.walk():")
for current_dir, dirs, files in os.walk("."):
    print(f"current_dir = {current_dir}, subdirs = {dirs}, files = {files}")

os.chdir("./files")
shutil.copy("tests/test.txt", "tests/copy.txt")
shutil.copytree("tests", "tests/copy")
