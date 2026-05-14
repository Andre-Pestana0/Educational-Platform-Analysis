import sys
from functools import cmp_to_key

def read_lines(lines):
    for line in lines:
        line = line.rstrip('\n')
        if line:
            yield line

def compare(a, b):
    for i in range(3):
        if a['medals'][i] != b['medals'][i]:
            return b['medals'][i] - a['medals'][i]
    if a['name'] < b['name']:
        return -1
    elif a['name'] > b['name']:
        return 1
    return 0

teams = {}
gen = read_lines(sys.stdin)

try:
    while True:
        next(gen)  # skip event/modalidade name
        for i in range(3):
            team_name = next(gen)
            if team_name not in teams:
                teams[team_name] = {'name': team_name, 'medals': [0, 0, 0]}
            teams[team_name]['medals'][i] += 1
except StopIteration:
    pass

sorted_teams = sorted(teams.values(), key=cmp_to_key(compare))

print("Quadro de Medalhas")
for team in sorted_teams:
    print(f"{team['name']} {team['medals'][0]} {team['medals'][1]} {team['medals'][2]}")