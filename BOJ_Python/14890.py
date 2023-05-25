import sys

input = sys.stdin.readline


def crossTheRoad(road):
    for i in range(1, N):
        if abs(road[i] - road[i - 1]) > 1:
            return False

        # 올라가는 경사로 만들기
        if road[i] > road[i - 1]:
            for k in range(L):
                if i - k - 1 < 0 or road[i - 1] != road[i - k - 1] or check[i - k - 1]:
                    return False
                if road[i - 1] == road[i - k - 1]:
                    check[i - k - 1] = True

        # 내려가는 경사로 만들기
        elif road[i] < road[i - 1]:
            for k in range(L):
                if i + k >= N or road[i] != road[i + k] or check[i + k]:
                    return False
                if road[i] == road[i + k]:
                    check[i + k] = True
    return True


if __name__ == '__main__':
    N, L = map(int, input().split())
    board = [list(map(int, input().split())) for _ in range(N)]
    ans = 0

    for i in range(N):
        check = [False] * N
        if crossTheRoad([board[i][j] for j in range(N)]):
            ans += 1

    for i in range(N):
        check = [False] * N
        if crossTheRoad([board[j][i] for j in range(N)]):
            ans += 1

    print(ans)