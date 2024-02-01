#include <iostream>
#include <climits>
#define MAX_NM 5

using namespace std;

int n, m;
int grid[MAX_NM][MAX_NM];
int ans = INT_MIN;

int getSum(int x, int y, int w, int h){           // 해당 직사각형 안에 있는 숫자 합을 구해주는 함수
    int curr = 0;
    for(int i=x; i<=x+h; i++){
        for(int j=y; j<=y+w; j++)
            curr += grid[i][j];
    }
    return curr;
}

int main(){
    // 입력:
    cin >> n >> m;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> grid[i][j];
        }
    }

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            // (i,j)는 첫번째 직사각형의 맨 왼쪽 위 점
            for(int w1=0; w1<m; w1++){
                for(int h1=0; h1<n; h1++){
                    if(i+h1 >= n || j+w1 >= m) continue;    // 만약 가로 세로가 범위를 벗어난다면 무시
                    for(int k=0; k<n; k++){
                        for(int l=0; l<m; l++){
                             // (k,l)은 두번째 직사각형의 맨 왼쪽 위 점
                            for(int w2=0; w2<m; w2++){
                                for(int h2=0; h2<n; h2++){
                                    if(k+h2 >= n || l+w2 >= m) continue;   // 만약 가로 세로 끝이 범위를 벗어난다면 무시
                                    if(i+h1 < k || j+w1 < l){
                                        // 두 직사각형이 겹치지 않는 경우에만 확인
                                        // 겹치지 않으려면 직사각형끼리 위아래에 있거나 좌우에 있기만 하면 
                                        int rectangle1 = getSum(i,j,w1,h1);
                                        int rectangle2 = getSum(k,l,w2,h2);
                                        ans = max(ans, rectangle1+rectangle2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    // 출력:
    cout << ans;
    return 0;
}