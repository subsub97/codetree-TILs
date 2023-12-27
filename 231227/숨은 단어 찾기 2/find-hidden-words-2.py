N,M=map(int,input().split())

arr = [list(map(str, input().strip())) for _ in range(N)] #strip(한글자씩 끊어서)

def in_range(x, y):
    return 0 <= x and x < N and 0 <= y and y < M

def check(arr):
    dxs, dys = [-1, -1, -1, 0, 0, 1, 1, 1], [-1, 0, 1, -1, 1, -1, 0, 1]
    cnt=0
    for i in range(N):
        for j in range(M):

            if arr[i][j]!='L':
                continue

            for dx,dy in zip(dxs,dys):
                if in_range(i+dx,j+dy)==False:
                    continue

                ix,jy=i+dx,j+dy

                if in_range(ix+dx,jy+dy)==False:
                    continue

                if arr[ix][jy]=='E' and arr[ix+dx][jy+dy]=='E':
                    cnt+=1

    print(cnt)

check(arr)