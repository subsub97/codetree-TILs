arr = [list(map(int, input().split())) for _ in range(19)]

black,white=1,2
dxs,dys=[1,1,1,0],[-1,0,1,1]
def in_range(x, y):
    return 0 <= x and x < 19 and 0 <= y and y < 19

def check(arr):
    for i in range(19):
        for j in range(19):
            if arr[i][j]==0:
                continue
            for dx,dy in zip(dxs,dys):
                cnt=0

                if in_range(i+dx,j+dy)==False:
                    continue
                while(1):
                    if arr[i][j] == arr[i+dx][j+dy]:
                        i, j = i + dx, j + dy
                        cnt+=1
                    else:
                        break
                    if (cnt==4):
                        print(f'{arr[i][j]}')
                        i,j=i-2*dx+1,j-2*dy+1
                        print(f'{i} {j}')

                        return 0

check(arr)