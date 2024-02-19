N,M=map(int,input().split())
count=0
array=[[0 for col in range(N+1)] for row in range(N+1)]
vistied=[False]*N


def dfs(v):
  global count
  vistied[v]=True
  for curr_v in range(1,N+1):
    if array[v][curr_v]==1 and vistied[curr_v]==False:
      vistied[curr_v]=True
      count+=1 
      dfs(curr_v)

for i in range(M):
  x,y= map(int,input().split())
  array[x][y]=1
  array[y][x]=1


vistied[1]=True
dfs(1)

print(count)