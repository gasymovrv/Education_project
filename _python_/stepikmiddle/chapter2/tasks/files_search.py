import os.path

os.chdir("./test_files")

res = []
for current_dir, dirs, files in os.walk("main"):
    for f in files:
        if f.endswith(".py"):
            res.append(current_dir.replace(os.sep, "/"))
            break

with open("result.txt", "w") as f:
    f.write("\n".join(sorted(res)))
