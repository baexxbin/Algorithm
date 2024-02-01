import sys

input = sys.stdin.readline


if __name__ == '__main__':
    N = int(input())
    buildings = list(map(int, input().split()))

    cnt = [0]*N

    for idx, cur in enumerate(buildings):   # 오른쪽 측면 검사
        mx_slop = float('-inf')
        x1 = idx+1
        y1 = cur
        for nxt in range(idx+1, N):
            x2 = nxt+1
            y2 = buildings[nxt]
            slop = (y2-y1) / (x2-x1)
            if slop > mx_slop:
                cnt[idx]+=1
                mx_slop = slop

    for idx, cur in reversed(list(enumerate(buildings))):   # 왼쪽 측면 검사
        mn_slop = float('inf')
        x1 = idx+1
        y1 = cur
        for nxt in range(idx-1, -1, -1):
            x2 = nxt+1
            y2 = buildings[nxt]
            slop = (y2 - y1) / (x2 - x1)
            if slop < mn_slop:
                cnt[idx]+=1
                mn_slop = slop

    print(max(cnt))

# 이때 x,y는 빌딩의 꼭대기 점 의미
