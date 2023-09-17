import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N, M, R = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(N)]

    # 돌려야 할 라인의 개수
    lines = min(N, M) // 2

    for _ in range(R):      # R만큼 돌려야함
        for idx in range(lines):
            x, y = idx, idx     # 각 라인의 시작점은 꼭짓점 (idx,idx)에 해당
            pre = matrix[x][y]  # 시작점 값 저장

            # for r in range(idx+1, N-idx):   # 왼쪽 측면 아래방향으로 이동
            #     cur = matrix[r][y]
            #     matrix[r][y] = pre
            #     pre = cur

            for r in range(idx+1, N-idx):   # 왼쪽 측면 아래방향으로 이동
                x = r
                cur = matrix[x][y]
                matrix[x][y] = pre
                pre = cur

            # for c in range(idx+1, M-idx):   # 아래 측면 오른쪽방향으로 이동
            #     cur = matrix[x][c]
            #     matrix[x][c] = pre
            #     pre = cur

            for c in range(idx+1, M-idx):   # 아래 측면 오른쪽방향으로 이동
                y = c
                cur = matrix[x][y]
                matrix[x][y] = pre
                pre = cur

            # for r in range(N-idx-1, 0+idx, -1):   # 오른쪽 측면 위쪽방향으로 이동
            #     cur = matrix[r][y]
            #     matrix[r][y] = pre
            #     pre = cur

            for r in range(idx+1, N-idx):   # 오른쪽 측면 위쪽방향으로 이동
                x = N - r -1
                cur = matrix[x][y]
                matrix[x][y] = pre
                pre = cur

            # for c in range(M-idx-1, 0+idx, -1):     # 위쪽 측면 오른쪽방향으로 이동
            #     cur = matrix[x][c]
            #     matrix[x][c] = pre
            #     pre = cur

            for c in range(idx+1, M-idx):     # 위쪽 측면 오른쪽방향으로 이동
                y = M -c-1
                cur = matrix[x][y]
                matrix[x][y] = pre
                pre = cur

    for row in matrix:
        print(' '.join(map(str, row)))





# 5:15 ~
# 하, 우, 상, 좌 순으로 돌아감(위치 말고 방향 기준)

# *초기 설계*
# 돌리기
# 각 돌아가는 라인을 원형큐에 담기
# 원형 큐에 담고, R번째 인덱스부터 출력

# 각 라인 구하기
# matrix[r:n-r] (if, row > n//2: r= n-r-1) , matrix[r:n-r]형태로 사각형 라인을 이룸
# 앞,뒤 끝트머리는 이전 row의 라인에 붙여주기..는 안됨 >> 다른 방법이용