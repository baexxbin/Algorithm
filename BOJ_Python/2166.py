import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N = int(input())
    points = [list(map(int, input().split())) for _ in range(N)]
    points.append(points[0])    # 마지막이랑 첫번째 연결

    left, right = 0, 0
    for i in range(N):
        left += points[i][0]*points[i+1][1]
        right += points[i][1]*points[i+1][0]

    area = abs(left - right) / 2
    print(round(area, 1))
