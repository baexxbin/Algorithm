import sys

input = sys.stdin.readline

# 3:34 - 4:24

if __name__ == '__main__':
    N = int(input())
    lines = [tuple(map(int, input().split())) for _ in range(N)]
    lines.sort(key=lambda x: x[0])

    cnt = 0
    start, end = lines[0]

    for i in range(1, N):
        cur_start, cur_end = lines[i]
        if start <= cur_start <= end:
            end = max(end, cur_end)
            continue
        cnt += end-start
        start, end = cur_start, cur_end
    cnt += end-start

    print(cnt)

# 같은 로직이였는데 처음에 덱에 넣어서 이용하려다 버벅임
# 덱에 리스트 형태를 넣을 때 주의, []로 리스트형태로 넣어도 각각의 원소로 인식..