from collections import deque

n, k = map(int, input().split())
maps = []
start = []

for _ in range(n):
    maps.append(list(map(int, input().split())))

visited = [[False for _ in range(n)] for _ in range(n)]
for _ in range(k):
    x, y = map(int, input().split())
    x -= 1
    y -= 1
    start.append([x, y])

q = deque()



def in_range(i, j):
    return 0 <= i and i < n and 0 <= j and j < n


def can_go(i, j):
    if not in_range(i, j):
        return False

    if visited[i][j] or maps[i][j] == 1:
        return False

    return True


def bfs():
    while q:
        row, col = q.popleft()
        drs, drc = [0, 1, 0, -1], [1, 0, -1, 0]
        for dr, dc in zip(drs, drc):
            new_r, new_c = row + dr, col + dc
            if can_go(new_r, new_c):
                q.append((new_r, new_c))
                visited[new_r][new_c] = True


for i in range(len(start)):
    row, col = start[i][0], start[i][1]
    q.append((row, col))
    visited[row][col]=True
    bfs()

count =0
for i in visited:
    for x in i:
        if x==True:
            count+=1
print(count)