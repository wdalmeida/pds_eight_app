import subprocess

subprocess.Popen("timeout --preserve-status -k 2s 10s stdbuf -oL explorenfc-cardemulation>>log.txt", shell=True, stdout=subprocess.PIPE).stdout.read()
filename = "log.txt"

with open(filename) as f:
    content = f.readlines()

line= content[-1]
if "Card" not in line:
        print(line[line.find("  ")+2:])







