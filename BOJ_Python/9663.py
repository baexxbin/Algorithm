import sys

input = sys.stdin.readline


def backtracking(row):      # row행에서 for문을 돌며 퀸 놓을 위치 정하기
    global cnt

    if row==N:
        cnt+=1
        return

    for col in range(N):
        if checkCol[col] or checkDiagoUp[row+col] or checkDiagoDown[row-col+N-1]:
            continue
        checkCol[col], checkDiagoUp[row+col], checkDiagoDown[row-col+N-1] = True, True, True
        backtracking(row+1)
        checkCol[col], checkDiagoUp[row+col], checkDiagoDown[row-col+N-1] = False, False, False


if __name__ == '__main__':
    N = int(input())

    checkCol = [False]*N
    checkDiagoUp = [False]*(N*2-1)
    checkDiagoDown = [False]*(N*2-1)
    cnt = 0

    backtracking(0)
    print(cnt)


# 초기 설계
# 이전 퀸에 의해 다음 퀸이 정해짐
# 행, 열, 대각선 고려
#   - 행: 한 행에 한 퀸으로 특별히 고려X
#   - 열: 이전 퀸이 간 열 체크
#   - 대각선: visited배열 이용 >> O(N)추가로 더 소요 됨 >> 수학적 방법 접근으로 변경

# **어려움을 겪은 부분**
# 0행만 검사하고 그에따른 n행에서 for문을 돌면서 퀸을 놓아 봄
#   - 0행에 대해서만 for문을 도는 것이 아닌, 'n번째 행'을 기준으로 백트래킹 진행
#   - 백트래킹안에서 n행에 대해 for문을 통해 열하나씩 돌려보기

# 같은 대각선이 있는지 판별
#   - visied배열로 대각선을 쭉 도는 것은 비효율적
#   - 우상향 대각선: x+y값이 같음
#   - 좌하향 대각선: x-y값이 같음 (배열로 관리하기 위해 x-y+n-1값 이용)