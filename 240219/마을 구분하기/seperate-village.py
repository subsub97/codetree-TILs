n = int(input())
max_village = []
village = []
for _ in range(n):
    village.append(list(map(int, input().split())))

visited = [[False for _ in range(n)] for _ in range(n)]

people_num=0 
def in_range(x, y):
    return 0 <= x and x < n and 0 <= y and y < n


def can_go(x, y):
    if not in_range(x, y):
        return False

    if visited[x][y] == True or village[x][y] == 0:
        return False

    return True


def dsf(x, y):
    global people_num
    dxs, dys = [0, -1, 0, 1],[1, 0, -1, 0]

    for dx, dy in zip(dxs, dys):
        new_x, new_y = x + dx, y + dy
        if can_go(new_x, new_y):
            visited[new_x][new_y] = True
            people_num += 1
            dsf(new_x, new_y)


for x in range(n):
    for y in range(n):
        if can_go(x,y):
            visited[x][y]=True
            people_num=1
            dsf(x,y)

            max_village.append(people_num)


max_village.sort()
print(len(max_village))
for x in max_village:
    print(x)