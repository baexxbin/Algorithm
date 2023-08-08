import sys

input = sys.stdin.readline


def draw():
    start, end = lines[0][0], lines[0][1]
    cnt = 0
    for i in range(1, N):
        cur_start, cur_end = lines[i]
        if end > cur_start:
            end = max(end, cur_end)
            continue
        cnt += (end - start)
        start, end = cur_start, cur_end
    cnt += (end - start)
    return cnt


if __name__ == '__main__':
    N = int(input())
    lines = [tuple(map(int, input().split())) for _ in range(N)]
    lines.sort()
    print(draw())